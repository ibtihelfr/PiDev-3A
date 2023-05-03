<?php

namespace App\Entity;

use App\Repository\TypeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=TypeRepository::class)
 */

class Type
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
     * @Assert\Length(max=100,maxMessage="Le type ne doit pas contenir que {{ limit }} caractéres")
     * @Assert\NotNull(message= "Ne doit pas être null")
     */
    private ?string $NomType = null;

    /**
     * @ORM\OneToMany(targetEntity="Reclamation", mappedBy="type")
     */
    private Collection $reclamations;

    public function __construct()
    {
        $this->reclamations = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomType(): ?string
    {
        return $this->NomType;
    }

    public function setNomType(string $NomType): self
    {
        $this->NomType = $NomType;

        return $this;
    }

    /**
     * @return Collection<int, Reclamation>
     */
    public function getReclamations(): Collection
    {
        return $this->reclamations;
    }

    public function addReclamation(Reclamation $reclamation): self
    {
        if (!$this->reclamations->contains($reclamation)) {
            $this->reclamations->add($reclamation);
            $reclamation->setType($this);
        }

        return $this;
    }

    public function removeReclamation(Reclamation $reclamation): self
    {
        if ($this->reclamations->removeElement($reclamation)) {
            // set the owning side to null (unless already changed)
            if ($reclamation->getType() === $this) {
                $reclamation->setType(null);
            }
        }

        return $this;
    
    
    }

    public function __toString(){
        return $this->NomType;
    }

}
