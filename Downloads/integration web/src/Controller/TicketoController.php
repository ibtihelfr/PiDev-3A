<?php

namespace App\Controller;

use App\Entity\Ticketo;
use App\Form\SendMailType;
use App\Form\TicketoType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/ticketo')]
class TicketoController extends AbstractController
{
    #[Route('/', name: 'ticketo_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $ticketos = $entityManager
            ->getRepository(Ticketo::class)
            ->findAll();

        return $this->render('ticketo/index.html.twig', [
            'ticketos' => $ticketos,
        ]);
    }

    #[Route('/new', name: 'ticketo_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $ticketo = new Ticketo();
        $form = $this->createForm(TicketoType::class, $ticketo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($ticketo);
            $entityManager->flush();

            return $this->redirectToRoute('ticketo_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('ticketo/new.html.twig', [
            'ticketo' => $ticketo,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{numticketo}', name: 'ticketo_show', methods: ['GET'])]
    public function show(Ticketo $ticketo): Response
    {
        return $this->render('ticketo/show.html.twig', [
            'ticketo' => $ticketo,
        ]);
    }

    #[Route('/{numticketo}/edit', name: 'ticketo_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Ticketo $ticketo, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TicketoType::class, $ticketo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('ticketo_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('ticketo/edit.html.twig', [
            'ticketo' => $ticketo,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{numticketo}', name: 'ticketo_delete', methods: ['POST'])]
    public function delete(Request $request, Ticketo $ticketo, EntityManagerInterface $entityManager): Response
    {
        $entityManager->remove($ticketo);
        $entityManager->flush();

        return $this->redirectToRoute('ticketo_index', [], Response::HTTP_SEE_OTHER);
    }
    #[Route('/{numticketo}/email', name: 'sendMailToUser')]
    public function sendEmail(MailerInterface $mailer,Request $request,Ticketo $offre): Response
    {
        $form =$this->createForm(SendMailType::class,null);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid())
        {
            $mail=$form->get('mail')->getData();
            $email = (new Email())
                ->from('benameur.firas@esprit.tn')
                ->to((string)$mail)
                ->subject("Offre")
                ->html("Logement: ".$offre->getLogement()." avec la restauration ".$offre->getRestauration()." a un prix de ".$offre->getPrix()."DT");
            $mailer->send($email);
            $this->addFlash('success', 'votre email a ete bien envoyé');
            return $this->redirectToRoute('ticketo_index');
        }
        return $this->render('admin/sendMail.html.twig', ['form' => $form->createView()]);
    }
}
