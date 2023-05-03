<?php

namespace App\Entity;

use App\Repository\ReponseRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ReponseRepository::class)
 */
class Reponse
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private ?int $id = null;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Le message ne doit pas être vide")
     * @Assert\Length(max=250, maxMessage="Le message ne doit contenir que {{ limit }} caractéres")
     */
    private ?string $Message = null;

    /**
     * @ORM\Column(type="date")
     */
    private ?\DateTimeInterface $DateReponse = null;

    /**
     * @ORM\ManyToOne(inversedBy="reponse")
     */
    private ?Reclamation $reclamation = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMessage(): ?string
    {
        return $this->Message;
    }

    public function setMessage(string $Message): self
    {
        $this->Message = $Message;

        return $this;
    }

    public function getDateReponse(): ?\DateTimeInterface
    {
        return $this->DateReponse;
    }

    public function setDateReponse(\DateTimeInterface $DateReponse): self
    {
        $this->DateReponse = $DateReponse;

        return $this;
    }

    public function getReclamation(): ?Reclamation
    {
        return $this->reclamation;
    }

    public function setReclamation(?Reclamation $reclamation): self
    {
        $this->reclamation = $reclamation;

        return $this;
    }
}
