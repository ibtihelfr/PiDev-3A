<?php
 
namespace App\Controller;
 
use Doctrine\Persistence\ManagerRegistry;
use Stripe;
use App\Entity\Panier;
use App\Entity\User;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Mime\Email;
use Symfony\Component\Validator\Constraints\DateTime;
use Symfony\Component\Security\Core\Security;

class StripeController extends AbstractController
{
    #[Route('/stripe', name: 'app_stripe')]
    public function index(ManagerRegistry $doctrine): Response
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
        $totalPrice = 0;
 
        foreach ($panier as $item) {
            $product = $item->getIdProduct();
            $quantity = $item->getQuantite();
            $totalPrice += $product->getPrixproduit() * $quantity;
        }
 
        return $this->render('stripe/index.html.twig', [
            'stripe_key' => $_ENV["STRIPE_KEY"],
            'totalPrice' => $totalPrice+3,
        ]);
    }
 
 
    #[Route('/stripe/create-charge', name: 'app_stripe_charge', methods: ['POST'])]
    public function createCharge(Request $request,ManagerRegistry $doctrine)
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
        $totalPrice = 0;
 
        foreach ($panier as $item) {
            $product = $item->getIdProduct();
            $quantity = $item->getQuantite();
            $totalPrice += $product->getPrixproduit() * $quantity;
        }
        Stripe\Stripe::setApiKey($_ENV["STRIPE_SECRET"]);
        Stripe\Charge::create ([
                "amount" => ($totalPrice+3) * 100,
                "currency" => "usd",
                "source" => $request->request->get('stripeToken'),
                "description" => "Binaryboxtuts Payment Test"
        ]);
        $this->addFlash(
            'success',
            'Payment Successful!',
        );
        return $this->redirectToRoute('app_stripe', [], Response::HTTP_SEE_OTHER);
    }
        // Injecter le composant Security dans le constructeur
public function __construct(Security $security)
{
    $this->security = $security;
}
}