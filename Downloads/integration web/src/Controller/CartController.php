<?php
 
namespace App\Controller;
use App\Entity\Panier;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Security;

 
class CartController extends AbstractController
{
    #[Route('/cart', name: 'app_cart')]
 
    public function index( ManagerRegistry $doctrine): Response
    {
         // Récupérer l'utilisateur connecté
         $user1 = $this->security->getUser();
        
         // Vérifier que l'utilisateur est connecté
         if (!$user1) {
             throw $this->createAccessDeniedException('You must be logged in to access this page.');
         }
        $entityManager=$doctrine->getManager();

        $user = $user1 ;
        $panier = $entityManager->getRepository(Panier::class)->findBy([
            'idClient' => $user
        ]);
        $totalPrice = 0;
 
        foreach ($panier as $item) {
            $product = $item->getIdProduct();
            $quantity = $item->getQuantite();
            $totalPrice += $product->getPrixproduit() * $quantity;
        }
        $count=count($panier);
 
        return $this->render('cart.html.twig', [
            'controller_name' => 'CartController',
            'panier' => $panier,
            'total' => $totalPrice,
            'panier_count' => $count
 
        ]);
    }
    
    #[Route('/cart/remove/{idpanier}',name:'delete_panier')]
 
    public function delete($idpanier,ManagerRegistry $doctrine) {
       // $idUsercon=1;
        $entityManager=$doctrine->getManager();
        $panier = $entityManager->getRepository(Panier::class)->findOneBy([
            'idpanier' => $idpanier
        ]);
 
        $entityManager->remove($panier);
        $entityManager->flush();
 
        $response = new Response();
        $response->send();
        return $this->redirectToRoute('app_cart');
        } 
 
 
    #[Route('/cart/increment/{idProduit}',name:'increment_panier')]
 
    public function increment(ManagerRegistry $doctrine, $idProduit) {
          // Récupérer l'utilisateur connecté
          $user = $this->security->getUser();
         
        // Vérifier que l'utilisateur est connecté
          if (!$user) {
            throw $this->createAccessDeniedException('You must be logged in to access this page.');
        }

       $idUsercon=$user->getIduser();

       $entityManager=$doctrine->getManager();
        $paniers = $entityManager->getRepository(Panier::class)->findBy([
            'idClient' => $idUsercon,
            'idProduct' => $idProduit
        ]);
 
        foreach ($paniers as $panier) {
            $quantite = $panier->getQuantite();
                $panier->setQuantite($quantite + 1);
                $entityManager->persist($panier);
                $entityManager->flush();
        }
        return $this->redirectToRoute('app_cart');
        }
        #[Route('/cart/decrement/{idProduit}',name:'decrement_panier')]
 
        public function decrement($idProduit,ManagerRegistry $doctrine) {
             // Récupérer l'utilisateur connecté
          $user = $this->security->getUser();
         
          // Vérifier que l'utilisateur est connecté
            if (!$user) {
              throw $this->createAccessDeniedException('You must be logged in to access this page.');
          }
  
         $idUsercon=$user->getIduser();
  
            $entityManager=$doctrine->getManager();
            $paniers = $entityManager->getRepository(Panier::class)->findBy([
                'idClient' => $idUsercon,
                'idProduct' => $idProduit
            ]);
 
            foreach ($paniers as $panier) {
                $quantite = $panier->getQuantite();
                if ($quantite > 1) {
                    $panier->setQuantite($quantite - 1);
                    $entityManager->persist($panier);
                    $entityManager->flush();
                }
            }
 
 
            return $this->redirectToRoute('app_cart');
            }
                // Injecter le composant Security dans le constructeur
public function __construct(Security $security)
{
    $this->security = $security;
}
 
}