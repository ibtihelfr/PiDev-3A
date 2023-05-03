<?php

namespace App\Controller;
use App\Entity\Commande;

use App\Repository\CommandeRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CommandeController extends AbstractController
{
    #[Route('/commande', name: 'app_commande')]
    public function index(CommandeRepository $repository): Response
    {
        $list=$repository->findAll();
        return $this->render('commande/commande.html.twig', [
            'controller_name' => 'CommandeController',
            'list'=>$list
        ]);
    }
    
    #[Route('/commande/remove/{idc}',name:'delete_commande')]

    public function delete($idc,ManagerRegistry $doctrine) {
        $entityManager=$doctrine->getManager();
        $commande = $entityManager->getRepository(Commande::class)->findOneBy([
            'idc' => $idc
        ]);
    
        
if ($commande) {
    $entityManager->remove($commande);
    $entityManager->flush();
}
        $response = new Response();
        $response->send();
        return $this->redirectToRoute('app_commande');
        }
        #[Route('/commande/modifier/{idc}',name:'modifier_commande')]

        public function modifierEtat($idc,ManagerRegistry $doctrine)
        {
            $entityManager=$doctrine->getManager();
            $commande = $entityManager->getRepository(Commande::class)->findOneBy([
                'idc' => $idc
            ]);
            $commande->setEtat(1);
            $entityManager->persist($commande);
            $entityManager->flush();
            return $this->redirectToRoute('app_commande');

   
        }

}