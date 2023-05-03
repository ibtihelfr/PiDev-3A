<?php


namespace App\Controller;

use PhpInsight\Insight;
use App\Entity\Reponse;
use App\Form\ReponseType;
use App\Repository\ReclamationRepository;
use App\Repository\ReponseRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/reponse')]
class ReponseController extends AbstractController
{
    #[Route('/', name: 'app_reponse_index', methods: ['GET'])]
    public function index(ReponseRepository $reponseRepository): Response
    {
        return $this->render('reponse/index.html.twig', [
            'reponses' => $reponseRepository->findAll(),
        ]);
    }
    #[Route('/{id}/newF', name: 'app_reponse_new_front', methods: ['GET', 'POST'])]
    public function newFront($id , ReclamationRepository $rp,Request $request, ReponseRepository $reponseRepository): Response
    {
        $reponse = new Reponse();
        $reponse->setReclamation($rp->find($id));
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reponse->setDateReponse(new \DateTime('now'));
            $rr = $this->filterwords($reponse->getMessage());

            $reponse->setMessage($rr);

            $reponseRepository->save($reponse, true);

            return $this->redirectToRoute('app_reclamation_show', ['id'=>$id], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reponse/newFront.html.twig', [
            'reponse' => $reponse,
            'form' => $form,
        ]);
    }
    #[Route('/{id}/new', name: 'app_reponse_new', methods: ['GET', 'POST'])]
    public function new($id , ReclamationRepository $rp,Request $request, ReponseRepository $reponseRepository): Response
    {
        $reponse = new Reponse();
        $reponse->setReclamation($rp->find($id));
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reponse->setDateReponse(new \DateTime('now'));
            $rr = $this->filterwords($reponse->getMessage());

            $reponse->setMessage($rr);
            $reponseRepository->save($reponse, true);

            return $this->redirectToRoute('app_reclamation_show_reponse', ['id'=>$id], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reponse/new.html.twig', [
            'reponse' => $reponse,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_reponse_show', methods: ['GET'])]
    public function show(Reponse $reponse): Response
    {
        return $this->render('reponse/show.html.twig', [
            'reponse' => $reponse,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_reponse_edit', methods: ['GET', 'POST'])]
    public function edit($id ,Request $request, Reponse $reponse, ReponseRepository $reponseRepository): Response
    {
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reponseRepository->save($reponse, true);
            $rr = $this->filterwords($reponse->getMessage());

            $reponse->setMessage($rr);

            return $this->redirectToRoute('app_reclamation_show_reponse', ['id'=>$reponse->getReclamation()->getId()]);
        }

        return $this->renderForm('reponse/edit.html.twig', [
            'reponse' => $reponse,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_reponse_delete', methods: ['POST'])]
    public function delete($id ,Request $request, Reponse $reponse, ReponseRepository $reponseRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reponse->getId(), $request->request->get('_token'))) {
            $id = $reponse->getReclamation()->getId();
            $reponseRepository->remove($reponse, true);
            return $this->redirectToRoute('app_reclamation_show_reponse', ['id'=>$id]);
        }
        if ($this->isCsrfTokenValid('deletee'.$reponse->getId(), $request->request->get('_tokenn'))) {
            $id = $reponse->getReclamation()->getId();
            $reponseRepository->remove($reponse, true);
            return $this->redirectToRoute('app_reclamation_show', ['id'=>$id]);
        }

        $id = $reponse->getReclamation()->getId();

        return $this->redirectToRoute('app_reclamation_show_reponse', ['id'=>$id]);    }

   public function filterwords($text)
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

    
}
    
