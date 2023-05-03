<?php

namespace App\Form;

use App\Entity\Reclamation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Validator\Constraints\File;


class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('Libelle')
            ->add('Description', TextareaType::class, [
                'label' => 'Description',
                'attr' => [
                    'rows' => 5,
                    'cols' => 70,// Specify number of rows for the textarea
                ],
            ])
            //->add('DateReclamation')
           //->add('EtatReclamation')
            ->add('Photo', FileType::class,['label' => 'Image',
                'mapped'=> 'false',
                'required' => 'false',
    /*'constraints' => [new File([
                    'maxSize' => '1024K',
                    'mimeTypes' => [
                        'image/jpeg',
                        'image/png'
                         ],
                    'mimeTypesMessage'=>'type du fichier invalide (png,jpeg,jpg&)'
                ])]*/


                ])
            /*->add('photo', FileType::class, [
                'label' => 'Photo (JPEG, PNG, or GIF file)',
                'mapped' => false, // This field is not mapped to a property on the entity
                'required' => false, // Allow the field to be empty
            ])*/
            ->add('type')
            ->add('save', SubmitType::class, [
                'label' => 'Envoyer',
                'attr' => [
                    'class' => 'btn btn-primary',
                ],
            ])
           //->add('id_user')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
