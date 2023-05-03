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
use Symfony\Component\Security\Core\Security;
use App\Entity\User;
use App\Entity\Panier;




#[Route('/produit')]
class ProduitController extends AbstractController
{
    
    #[Route('/new', name: 'app_produit_new', methods: ['GET', 'POST'])]
    public function new(Request $request, ProduitRepository $produitRepository): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->save($produit, true);


            // handle image upload
        $photop = $form->get('photop')->getData();
        if ($photop) {
            $originalFilename = pathinfo($photop->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = $originalFilename.'.'.$photop->guessExtension();
        
            $photop->move(
                $this->getParameter('event_images_directory'),
                $newFilename
            );
        
            $produit->setPhotop($newFilename);

        }
            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }
    #[Route('/', name: 'app_produit_index', methods: ['GET'])]
    public function index(ProduitRepository $produitRepository,PaginatorInterface $paginator,Request $request): Response
    {
        $produits =$produitRepository->findAll();
        $pagination = $paginator->paginate(
            $produits,
            $request->query->getInt('page', 1), 4
        );

        return $this->render('produit/index.html.twig', [
            'pagination' => $pagination,
        ]);
    }

    #[Route('/{idproduit}', name: 'app_produit')]
    public function add($idproduit,\Doctrine\Persistence\ManagerRegistry $doctrine): Response
    {
        $entityManager=$doctrine->getManager();
         // Récupérer l'utilisateur connecté
         $user = $this->security->getUser();
        
         // Vérifier que l'utilisateur est connecté
         if (!$user) {
             throw $this->createAccessDeniedException('You must be logged in to access this page.');
         }


        $utilisateur = $entityManager->getRepository(User::class)->find($user);
        $produit = $entityManager->getRepository(Produit::class)->find($idproduit);
        // Create a new Basket entity
        $panier = $entityManager->getRepository(Panier::class)->findOneBy([
            'idClient' => $utilisateur,
            'idProduct' => $produit
        ]);
    
        if ($panier) {
            // If the product is already in the cart, increment the quantity
            $quantite = $panier->getQuantite();
            $panier->setQuantite($quantite + 1);
        } else {
            // If the product is not in the cart, create a new Basket entity with quantity = 1
            $panier = new Panier();
            $panier->setIdClient($utilisateur);
            $panier->setIdProduct($produit);
            $panier->setQuantite(1);

        }
        $panier->setTotale($panier->getQuantite()*$panier->getIdProduct()->getPrixproduit());
        

        // Persist the entity to the database
        $entityManager->persist($panier);
        $entityManager->flush();

        // Return a response to indicate success
        return $this->redirectToRoute('app_produit_index');
    }
// Injecter le composant Security dans le constructeur
public function __construct(Security $security)
{
    $this->security = $security;
}


    #[Route('/{idproduit}', name: 'app_produit_show', methods: ['GET'])]
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    #[Route('/{idproduit}/edit', name: 'app_produit_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->save($produit, true);
            
            $uploadedFile = $form['photop']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/uploads/events';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFile = $originalFilename.'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFile
               );
               $produit->setPhotop($newFile);


            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form,
        ]);
    }

    #[Route('/{idproduit}', name: 'app_produit_delete', methods: ['POST'])]
    public function delete(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getIdproduit(), $request->request->get('_token'))) {
            $produitRepository->remove($produit, true);
        }

        return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
    }
}
