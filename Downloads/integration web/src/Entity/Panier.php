<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Panier
 *
 * @ORM\Table(name="panier", indexes={@ORM\Index(name="id_product", columns={"id_product"}), @ORM\Index(name="id_client", columns={"id_client"})})
 * @ORM\Entity
 */
class Panier
{
    /**
     * @var int
     *
     * @ORM\Column(name="idpanier", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idpanier;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var float|null
     *
     * @ORM\Column(name="totale", type="float", precision=10, scale=0, nullable=true)
     */
    private $totale;

    /**
     * @var Produit
     *
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_product", referencedColumnName="IdProduit")
     * })
     */
    private $idProduct;

    /**
     * @var User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="IdUser")
     * })
     */
    private $idClient;

    public function getIdpanier(): ?int
    {
        return $this->idpanier;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getTotale(): ?float
    {
        return $this->totale;
    }

    public function setTotale(?float $totale): self
    {
        $this->totale = $totale;

        return $this;
    }

    public function getIdProduct(): ?Produit
    {
        return $this->idProduct;
    }

    public function setIdProduct(?Produit $idProduct): self
    {
        $this->idProduct = $idProduct;

        return $this;
    }

    public function getIdClient(): ?User
    {
        return $this->idClient;
    }

    public function setIdClient(?User $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }


}
