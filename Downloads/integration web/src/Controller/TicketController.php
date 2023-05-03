<?php

namespace App\Controller;

use App\Entity\Event;
use App\Entity\Reservation;
use App\Entity\Ticket;
use App\Entity\User;
use App\Form\BookingType;
use App\Form\TicketType;
use Doctrine\ORM\EntityManagerInterface;
use Dompdf\Dompdf;
use Endroid\QrCode\QrCode;
use Endroid\QrCode\Writer\PngWriter;
use FOS\UserBundle\Security\UserProvider;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Security;

#[Route('/ticket')]
class TicketController extends AbstractController
{
    #[Route('/', name: 'ticket_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $tickets = $entityManager
            ->getRepository(Ticket::class)
            ->findAll();

        return $this->render('ticket/index.html.twig', [
            'tickets' => $tickets,
        ]);
    }
    #[Route('/front', name: 'ticket_index_front', methods: ['GET'])]
    public function indexClient(EntityManagerInterface $entityManager): Response
    {
        $tickets = $entityManager
            ->getRepository(Ticket::class)
            ->findAll();
        return $this->render('ticket/index_front.html.twig', [
            'tickets' => $tickets,
        ]);
    }

    #[Route('/{id}/front', name: 'ticket_front', methods: ['GET'])]
    public function indexEventClient(EntityManagerInterface $entityManager,int $id): Response
    {
        $tickets = $entityManager
            ->getRepository(Ticket::class)
            ->findBy(['idevent'=>$id]);
        return $this->render('ticket/index_front.html.twig', [
            'tickets' => $tickets,
        ]);
    }

    #[Route('/new', name: 'ticket_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $ticket = new Ticket();
        $form = $this->createForm(TicketType::class, $ticket);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($ticket);
            $entityManager->flush();

            return $this->redirectToRoute('ticket_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('ticket/new.html.twig', [
            'ticket' => $ticket,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{numticket}', name: 'ticket_show', methods: ['GET'])]
    public function show(Ticket $ticket): Response
    {
        return $this->render('ticket/show.html.twig', [
            'ticket' => $ticket,
        ]);
    }

    #[Route('/{numticket}/edit', name: 'ticket_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Ticket $ticket, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(TicketType::class, $ticket);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('ticket_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('ticket/edit.html.twig', [
            'ticket' => $ticket,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{numticket}/book', name: 'ticket_book', methods: ['GET', 'POST'])]
    public function book(Request $request, Ticket $ticket, EntityManagerInterface $entityManager): Response
    {
        $old=$ticket->getNbtdemande();
        $form = $this->createForm(BookingType::class, $ticket);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $demand=$ticket->getNbtdemande();
            $ticket->setNbtdemande($old-$demand);
            $user=$this->getUser();
         
            $res=new Reservation();
            $res->setIdevent($ticket->getIdevent());
            if ($user instanceof User){
                $res->setIduser($user);
            }
            $ticket->setIdres($res);
            $entityManager->flush();
            $ticket->setNbtdemande($demand);

            $writer = new PngWriter();
            $qrCode = new QrCode($ticket->getIdevent()->getNomevent());
            $qrCode->setSize(100);
            $result=$writer->write($qrCode);

            $html =  $this->renderView('ticket/pdf.html.twig', ['ticket'=>$ticket,'qrCode'=>base64_encode($result->getString())]);
            $dompdf = new Dompdf();
            $dompdf->loadHtml($html);
            $dompdf->render();

            return new Response (
                $dompdf->stream('Ticket', ["Attachment" => false]),
                Response::HTTP_OK,
                ['Content-Type' => 'application/pdf']
            );
        }

        return $this->render('ticket/book.html.twig', [
            'ticket' => $ticket,
            'form' => $form->createView(),
        ]);
    }

    #[Route('/{numticket}/QR', name: 'ticket_qr', methods: ['GET'])]
    public function qrCodeAction(Ticket $ticket)
    {
        $writer = new PngWriter();
        $qrCode = new QrCode($ticket->getIdevent()->getNomevent());
        $qrCode->setSize(300);
        $result=$writer->write($qrCode);
        header('Content-Type: '.$result->getMimeType());
        echo $result->getString();
        exit();
    }

    #[Route('/{numticket}', name: 'ticket_delete', methods: ['POST'])]
    public function delete(Request $request, Ticket $ticket, EntityManagerInterface $entityManager): Response
    {
        $entityManager->remove($ticket);
        $entityManager->flush();

        return $this->redirectToRoute('ticket_index', [], Response::HTTP_SEE_OTHER);
    }
    #[Route('/pdf', name: 'pdf_generator')]
    public function PDFTicket(Ticket $ticket): Response
    {
        $html =  $this->renderView('ticket/pdf.html.twig', ['ticket'=>$ticket]);
        $dompdf = new Dompdf();
        $dompdf->loadHtml($html);
        $dompdf->render();

        return new Response (
            $dompdf->stream('Ticket', ["Attachment" => false]),
            Response::HTTP_OK,
            ['Content-Type' => 'application/pdf']
        );
    }
}
