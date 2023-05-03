<?php
 
namespace App\Controller;
use App\Entity\Commande;
use App\Entity\User;
use App\Entity\Panier;
use App\Form\AjouterCommandeType;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use DateTimeImmutable;
use Symfony\Component\Security\Core\Security;

 
class CheckoutController extends AbstractController
{
    #[Route('/checkout', name: 'app_checkout')]
    public function index(\Doctrine\Persistence\ManagerRegistry $doctrine , Request $request): Response
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
            $totalPrice += $product->getPrixproduit() * $quantity ;
        }
        $count=count($panier);
        

       // foreach ($user as $item) {
            $nomU=$user->getNomuser();
            $prenomU=$user->getPrenomuser();
            $emailU=$user->getEmail();
            $num=$user->getNumtel();
        //} 
    
    
        $user = $entityManager->getRepository(User::class)->find($user); 
 
        $form = $this->createForm(AjouterCommandeType::class);
        $form->handleRequest($request);
 
            $data = $form->getData();
            $nom = $form->get('nom')->getData();
            $prenom = $form->get('prenom')->getData();
            $email = $form->get('email')->getData();
            $commande=new Commande();
            $commande->setDate(new DateTimeImmutable());
            $commande->setIdClient($user);
            $commande->setTotale($totalPrice+3 );
            $commande->setEtat(0);
            $commande->setNom($nomU);
            $commande->setPrenom($prenomU);
            $commande->setEmail($emailU);
            $entityManager->persist($commande);
            $entityManager->flush();
 

        

 
        return $this->render('checkout/checkout.html.twig', [
            'totale'=>$totalPrice,
            'panier'=>$panier,
            'emailU'=> $emailU,
            'prenomU'=>$prenomU,
            'nomU'=>$nomU,
            'form' => $form->createView(),
        ]);
    }

    // Injecter le composant Security dans le constructeur
public function __construct(Security $security)
{
    $this->security = $security;
}
    public function count( ManagerRegistry $doctrine): Response
    {
        // Récupérer l'utilisateur connecté
        $user = $this->security->getUser();
         
        // Vérifier que l'utilisateur est connecté
          if (!$user) {
            throw $this->createAccessDeniedException('You must be logged in to access this page.');
        }

       $idUsercon=$user->getIduser();
        $entityManager=$doctrine->getManager();
        $panier = $entityManager->getRepository(Panier::class)->findBy([
            'idClient' => $idUsercon
        ]);
        $count=count($panier);
 
        return $this->render('cart.html.twig', [
 
            'panier_count' => $count
 
        ]);
    }
 
 
    /**
     * @Route("/confirmation", name="confirmation")
     */
    public function confirmation()
    {
        return $this->render('confirmation.html.twig');
    }
}