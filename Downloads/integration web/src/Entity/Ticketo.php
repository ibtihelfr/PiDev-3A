<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Ticketo
 *
 * @ORM\Table(name="ticketo")
 * @ORM\Entity
 */
class Ticketo
{
    /**
     * @var int
     *
     * @ORM\Column(name="numTicketO", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $numticketo;

    /**
     * @var string
     *
     * @ORM\Column(name="Logement", type="string", length=255, nullable=false)
     */
    private $logement;

    /**
     * @var string
     *
     * @ORM\Column(name="Restauration", type="string", length=255, nullable=false)
     */
    private $restauration;

    /**
     * @var float
     * @Assert\NotNull(message="Indiquer un prix")
     * @Assert\Positive (message="Le prix doit etre positif")
     * @ORM\Column(name="Prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    public function getNumticketo(): ?int
    {
        return $this->numticketo;
    }

    public function getLogement(): ?string
    {
        return $this->logement;
    }

    public function setLogement(string $logement): self
    {
        $this->logement = $logement;

        return $this;
    }

    public function getRestauration(): ?string
    {
        return $this->restauration;
    }

    public function setRestauration(string $restauration): self
    {
        $this->restauration = $restauration;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }


}
