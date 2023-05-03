<?php
 
namespace App\Controller;
 
use Doctrine\Persistence\ManagerRegistry;
use Dompdf\Dompdf;
use App\Entity\Panier;
use App\Entity\Produit;
use App\Entity\Commande;
use App\Entity\User;
use App\Repository\ProduitRepository;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Security\Core\Security;

 
class PdfController extends AbstractController
{
 
    #[Route('/pdf1', name: 'app_pdf1')]
public function genererTicketAchatPDF(Request $request , ManagerRegistry $doctrine)
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
    try {
        // Create a new instance of Dompdf
        $dompdf = new Dompdf();
 
        // Render the PDF content using Twig
        $html = $this->renderView('pdf/index.html.twig', [
            'date' => new \DateTime(),
            'produits' => $panier,
        ]);
 
        // Load the HTML content into Dompdf
        $dompdf->loadHtml($html);
 
        // Set the paper size and orientation
        $dompdf->setPaper('A4', 'portrait');
 
        // Render the PDF
        $dompdf->render();
 
        // Return the PDF as a response
        return new Response(
            $dompdf->output(),
            Response::HTTP_OK,
            [
                'Content-Type' => 'application/pdf',
                'Content-Disposition' => 'attachment; filename="ticket_achat.pdf"',
            ]
        );
    } catch (\Exception $ex) {
        // Handle any errors that occur during PDF generation
        return new Response('Erreur lors de la génération du PDF: '.$ex->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
    }
}
        // Injecter le composant Security dans le constructeur
        public function __construct(Security $security)
        {
            $this->security = $security;
        }
 
}