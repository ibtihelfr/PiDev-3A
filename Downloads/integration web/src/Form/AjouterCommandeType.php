<?php

namespace App\Form;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use App\Entity\Commande;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
class AjouterCommandeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            
        ->add('nom', TextType::class, [
          'attr' => [
              'class' => 'form-control'
          ],
          'label' => 'nom'
      ])
      ->add('prenom', TextType::class, [
          'attr' => [
              'class' => 'form-control'
          ],
          'label' => 'prenom',
          
      ])
      ->add('email', EmailType::class, [
        'constraints' => [
            new Assert\NotBlank(),
            new Assert\Email(),
        ],
        'attr' => [
          'class' => 'form-control'
      ]
    ])
      
    
        ;
        
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Commande::class,
        ]);
    }
}