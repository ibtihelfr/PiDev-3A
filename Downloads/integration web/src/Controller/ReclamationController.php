<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;


use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\String\Slugger\SluggerInterface;

#[Route('/reclamation')]
class ReclamationController extends AbstractController
{
    #[Route('/', name: 'app_reclamation_index', methods: ['GET'])]
    public function index(Request $request,ReclamationRepository $reclamationRepository,PaginatorInterface $paginator): Response
    {
        $reclamations = $reclamationRepository->findAll();
        $reclamations = $paginator->paginate(
            $reclamations,
            $request->query->getInt('page', 1),
            limit: 2
        );

        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamations,
        ]);

    }
    #[Route('/back', name: 'app_reclamation_index_back', methods: ['GET'])]
    public function indexBack(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/indexBack.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_reclamation_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ReclamationRepository $reclamationRepository,MailerInterface $mailer,SluggerInterface $slugger): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
        $reclamation->setDateReclamation(new \DateTime('now'));
        $reclamation->setEtatReclamation("En attente");
        $filesystem = new Filesystem();

        if ($form->isSubmitted() && $form->isValid()) {
            $uploadfile = $form->get('Photo')->getData();
            $formData = $uploadfile->getPathname();
            $sourcePath=strval($formData);
            $destination = 'Pictures/photo'.rand(1,99999999999).'.png';
            $reclamation->setPhoto($destination);
            $filesystem->copy($sourcePath,$destination);

            $rr = $this->filterwords($reclamation->getDescription(),$mailer);
            $aa = $this->filterwords($reclamation->getLibelle(),$mailer);
            $reclamation->setDescription($rr);
            $reclamation->setLibelle($aa);

            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }
    #[Route('/{id}/reponse', name: 'app_reclamation_show_reponse', methods: ['GET'])]
    public function showReponse(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/showReponse.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }
    #[Route('/{id}', name: 'app_reclamation_show', methods: ['GET'])]
    public function show(Reclamation $reclamation): Response
    {

        return $this->render('reclamation/showReclamation.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }
    #[Route('/{id}/back', name: 'app_reclamation_show_back', methods: ['GET'])]
    public function showBack(Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        $r = $reclamation->getEtatReclamation();
        if($r=="En attente"){
        $reclamation->setEtatReclamation("Lu");
        $reclamationRepository->save($reclamation, true);
    }
        return $this->render('reclamation/showBack.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }
    #[Route('/{id}/edit', name: 'app_reclamation_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository,MailerInterface $mi): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $rr = $this->filterwords($reclamation->getDescription(),$mi);
            $aa = $this->filterwords($reclamation->getLibelle(),$mi);
            $reclamation->setDescription($rr);
            $reclamation->setLibelle($aa);
            $reclamationRepository->save($reclamation, true);

            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_reclamation_delete', methods: ['POST'])]
    public function delete(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation, true);
        }

        return $this->redirectToRoute('app_reclamation_index_back', [], Response::HTTP_SEE_OTHER);
    }
    public function filterwords($text,MailerInterface $mailer)
    {
        $filterWords = array('shit', 'khayeb', 'bheyem');
        $filterCount = count($filterWords);
        $str = "";
        $data = preg_split('/\s+/', $text);
        foreach ($data as $s) {
            $g = false;
            foreach ($filterWords as $lib) {
                if ($s == $lib) {
                    $t = "";
                    $email = (new Email())
                        ->from('hello@example.com')
                        ->to('asma.laaribi@esprit.tn')
                        //->cc('cc@example.com')
                        //->bcc('bcc@example.com')
                        //->replyTo('fabien@example.com')
                        //->priority(Email::PRIORITY_HIGH)
                        ->subject('Mots innapropriés!!')
                        ->text('Sending emails is fun again!')
                        ->html('<p>Votre réclamation contient des mots innapropriés , et va être supprimée!</p>');

                    $mailer->send($email);
                    for ($i = 0; $i < strlen($s); $i++) $t .= "*";
                    $str .= $t . " ";
                    $g = true;
                    break;
                }

            }
            if (!$g)
                $str .= $s . " ";
        }
        return $str;
    }

    #[Route('/newreclamation', name: 'newreclamation', methods: ['GET', 'POST'])]
    public function newreclamation(Request $request): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('Photo')->getData();

            $fileName = $this->generateUniqueFileName() . '.' . $file->guessExtension();

            // moves the file to the directory where brochures are stored
            $file->move(
                $this->getParameter('brochures_directory'),
                $fileName
            );
            $reclamation->setPhoto($fileName);


            $reclamation = $form->getData();

            $em = $this->getDoctrine()->getManager();
            $em->persist($reclamation);
            $em->flush();
            return $this->redirectToRoute('AfficheReclamationFront');
        }
        return $this->render('reclamation/index.html.twig', [
            'form' => $form->createView(),
        ]);
    }
}
