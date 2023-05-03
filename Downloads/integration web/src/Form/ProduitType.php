<?php

namespace App\Form;

use App\Entity\Produit;
use App\Entity\Categorie;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Form\Extension\Core\Type\FileType;


class ProduitType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomproduit')
            ->add('descproduit')
            ->add('dateproduit')
            ->add('prixproduit')
            ->add('qte')
            ->add('photop', FileType::class, [
                'data_class' => null,
                'required' => false,
                'label' => 'Upload image',
                'attr' => ['placeholder' => 'Photo.jpg',
                'class'=>"form-control-file"]
            ])
            ->add('idcategorie',EntityType::class, [
                'class' => Categorie::class,
                'choice_label' => 'NomCategorie',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
        ]);
    }
}
