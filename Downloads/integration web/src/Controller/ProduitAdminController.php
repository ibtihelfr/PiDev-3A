<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

#[Route('/admin/produit')]
class ProduitAdminController extends AbstractController
{
    #[Route('/', name: 'app_produit_back_index', methods: ['GET'])]
    public function index(ProduitRepository $produitRepository,PaginatorInterface $paginator,Request $request): Response
    {
        $produits =$produitRepository->findAll();
        $pagination = $paginator->paginate(
            $produits,
            $request->query->getInt('page', 1), 3
        );

        return $this->render('produitAdmin/index.html.twig', [
            'pagination' => $pagination,
        ]);
    }


    #[Route('/new', name: 'app_produit_back_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ProduitRepository $produitRepository): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->save($produit, true);

            return $this->redirectToRoute('app_produit_back_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produitAdmin/new.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{idproduit}', name: 'app_produit_back_show', methods: ['GET'])]
    public function show(Produit $produit): Response
    {
        return $this->render('produitAdmin/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    #[Route('/{idproduit}/edit', name: 'app_produit_back_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->save($produit, true);

            return $this->redirectToRoute('app_produit_back_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produitAdmin/edit.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{idproduit}', name: 'app_produit_back_delete', methods: ['POST'])]
    public function delete(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getIdproduit(), $request->request->get('_token'))) {
            $produitRepository->remove($produit, true);
        }

        return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
    }
}
