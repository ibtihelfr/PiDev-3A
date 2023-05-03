<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Form\CategorieType;
use App\Repository\CategorieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/admin/categorie')]
class CategorieAdminController extends AbstractController
{
    #[Route('/', name: 'app_categorie_back_index', methods: ['GET'])]
    public function index(CategorieRepository $categorieRepository): Response
    {
        return $this->render('categorieAdmin/index.html.twig', [
            'categories' => $categorieRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_categorie_back_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CategorieRepository $categorieRepository): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieRepository->save($categorie, true);

            return $this->redirectToRoute('app_categorie_back_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorieAdmin/new.html.twig', [
            'categorie' => $categorie,
            'form' => $form,
        ]);
    }
 
    #[Route('/{idcategorie}', name: 'app_categorie_back_show', methods: ['GET'])]
    public function show(Categorie $categorie): Response
    {
        return $this->render('categorieAdmin/show.html.twig', [
            'categorie' => $categorie,
        ]);
    }

    #[Route('/{idcategorie}/edit', name: 'app_categorie_back_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieRepository->save($categorie, true);

            return $this->redirectToRoute('app_categorie_back_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categorieAdmin/edit.html.twig', [
            'categorie' => $categorie,
            'form' => $form,
        ]);
    }

    #[Route('/{idcategorie}', name: 'app_categorie_back_delete', methods: ['POST'])]
    public function delete(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorie->getIdcategorie(), $request->request->get('_token'))) {
            $categorieRepository->remove($categorie, true);
        }

        return $this->redirectToRoute('app_categorie_back_index', [], Response::HTTP_SEE_OTHER);
    }
}
