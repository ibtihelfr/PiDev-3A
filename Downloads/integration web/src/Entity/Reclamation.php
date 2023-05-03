<?php

namespace App\Entity;

use App\Repository\ReclamationRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Table(name="Reclamation")
 * @ORM\Entity(repositoryClass=ReclamationRepository::class)
 */
class Reclamation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private ?int $id = null;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message= "Le champ libellé est obligatoire")
     * @Assert\Length(max=250,maxMessage="Le libellé ne peut contenir que {{ limit }} caractéres",)
     * @Assert\Regex(pattern= "/^[\p{L}\d\s\'-]+$/u", message="Le libellé doit avoir seulement des caractéres")
     */
    private ?string $Libelle = null;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="La description est obligatoire")
     * @Assert\Length(max=250, maxMessage="La description ne peut contenir que {{ limit }} caractéres")
     */
    private ?string $Description = null;

    /**
     * @ORM\Column(type="date")
     */
    private ?\DateTimeInterface $DateReclamation = null;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private ?string $EtatReclamation = null;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Vous devez insérer une photo")
     */
    private ?string $Photo = null;

    /**
     * @ORM\OneToMany(targetEntity="Reponse", mappedBy="reclamation")
     */
    private Collection $reponses;

    /**
     * @ORM\ManyToOne(inversedBy="reclamation")
     * @Assert\NotBlank(message="Vous devez choisr un type de réclamation")
     */
    private ?Type $type = null;

    /**
     * @ORM\ManyToOne(targetEntity="User", inversedBy="Reclamation")
     * @ORM\JoinColumn(name="user_id", referencedColumnName="IdUser")
     */
    private ?User $user = null;

    public function __construct()
    {
        $this->reponses = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLibelle(): ?string
    {
        return $this->Libelle;
    }

    public function setLibelle(string $Libelle): self
    {
        $this->Libelle = $Libelle;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    public function getDateReclamation(): ?\DateTimeInterface
    {
        return $this->DateReclamation;
    }

    public function setDateReclamation(\DateTimeInterface $DateReclamation): self
    {
        $this->DateReclamation = $DateReclamation;

        return $this;
    }

    public function getEtatReclamation(): ?string
    {
        return $this->EtatReclamation;
    }

    public function setEtatReclamation(string $EtatReclamation): self
    {
        $this->EtatReclamation = $EtatReclamation;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->Photo;
    }

    public function setPhoto(string $Photo): self
    {
        $this->Photo = $Photo;

        return $this;
    }

    /**
     * @return Collection<int, Reponse>
     */
    public function getReponses(): Collection
    {
        return $this->reponses;
    }

    public function addReponse(Reponse $reponse): self
    {
        if (!$this->reponses->contains($reponse)) {
            $this->reponses->add($reponse);
            $reponse->setReclamation($this);
        }

        return $this;
    }

    public function removeReponse(Reponse $reponse): self
    {
        if ($this->reponses->removeElement($reponse)) {
            // set the owning side to null (unless already changed)
            if ($reponse->getReclamation() === $this) {
                $reponse->setReclamation(null);
            }
        }

        return $this;
    }

    public function getType(): ?Type
    {
        return $this->type;
    }

    public function setType(?Type $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getIdUser(): ?User
    {
        return $this->id_user;
    }

    public function setIdUser(?User $id_user): self
    {
        $this->id_user = $id_user;

        return $this;
    }
    public function __toString(){
        return $this->Libelle;
    }
}
