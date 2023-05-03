<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

/**
 * Produit
 *
 * @ORM\Table(name="produit", indexes={@ORM\Index(name="IdCategorie", columns={"IdCategorie"})})
 * @ORM\Entity
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="IdProduit", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="NomProduit", type="string", length=255, nullable=false)
     */
    private $nomproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="DescProduit", type="string", length=255, nullable=false)
     */
    private $descproduit;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateProduit", type="date", nullable=false)
     */
    private $dateproduit;

    /**
     * @var float
     *
     * @ORM\Column(name="PrixProduit", type="float", precision=255, scale=0, nullable=false)
     */
    private $prixproduit;

    /**
     * @var int
     *
     * @ORM\Column(name="Qte", type="integer", nullable=false)
     */
    private $qte;

    /**
     * @var string
     *
     * @ORM\Column(name="PhotoP", type="string", length=255, nullable=false)
     */
    private $photop;

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="IdCategorie", referencedColumnName="IdCategorie")
     * })
     */
    private $idcategorie;

    public function getIdproduit(): ?int
    {
        return $this->idproduit;
    }

    public function getNomproduit(): ?string
    {
        return $this->nomproduit;
    }

    public function setNomproduit(string $nomproduit): self
    {
        $this->nomproduit = $nomproduit;

        return $this;
    }

    public function getDescproduit(): ?string
    {
        return $this->descproduit;
    }

    public function setDescproduit(string $descproduit): self
    {
        $this->descproduit = $descproduit;

        return $this;
    }

    public function getDateproduit(): ?\DateTimeInterface
    {
        return $this->dateproduit;
    }

    public function setDateproduit(\DateTimeInterface $dateproduit): self
    {
        $this->dateproduit = $dateproduit;

        return $this;
    }

    public function getPrixproduit(): ?float
    {
        return $this->prixproduit;
    }

    public function setPrixproduit(float $prixproduit): self
    {
        $this->prixproduit = $prixproduit;

        return $this;
    }

    public function getQte(): ?int
    {
        return $this->qte;
    }

    public function setQte(int $qte): self
    {
        $this->qte = $qte;

        return $this;
    }

    public function getPhotop(): ?string
    {
        return $this->photop;
    }

    public function setPhotop(string $photop): self
    {
        $this->photop = $photop;

        return $this;
    }

    public function getIdcategorie(): ?Categorie
    {
        return $this->idcategorie;
    }

    public function setIdcategorie(?Categorie $idcategorie): self
    {
        $this->idcategorie = $idcategorie;

        return $this;
    }


}
