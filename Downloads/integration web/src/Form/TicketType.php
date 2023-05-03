<?php

namespace App\Form;

use App\Entity\Ticket;
use App\Entity\Ticketo;
use Doctrine\ORM\Mapping\Entity;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\NotNull;
use Symfony\Component\Validator\Constraints\Positive;

class TicketType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nbmaxt',IntegerType::class,['attr' =>['class'=>'form-control']])
            ->add('nbtdemande',IntegerType::class,[
                'attr' =>['class'=>'form-control hidden']
            ])
            ->add('prixf',NumberType::class,['attr' =>['class'=>'form-control']])
            ->add('idevent',EntityType::class,[
                'class'=>'App\Entity\Event',
                'choice_label'=> 'nomevent',
                'attr' =>['class'=>'form-control']
            ])
            ->add('idres',EntityType::class,[
                'class'=>'App\Entity\Reservation',
                'choice_label'=>'idres',
                'attr' =>['class'=>'form-control']
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Ticket::class,
        ]);
    }
}
