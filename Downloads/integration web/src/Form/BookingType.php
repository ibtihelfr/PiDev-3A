<?php

namespace App\Form;

use App\Entity\Ticket;
use Doctrine\ORM\Mapping\Entity;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\FormEvent;
use Symfony\Component\Form\FormEvents;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Expression;

class BookingType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nbmaxt', IntegerType::class, [
                'attr' => ['class' => 'form-control'],
                'disabled' => True
            ])
            ->add('nbtdemande', IntegerType::class, [
                'attr' => ['class' => 'form-control'],
                'constraints' => [
                    new Expression('value<=' . $options['data']->getNbmaxt(), "Verifier le nombre max"),
                    new Expression('value<=' . $options['data']->getNbtdemande(), "Verifier le nombre max"),
                ]])
            ->add('prixf', NumberType::class, ['attr' => ['class' => 'form-control'], 'disabled' => True])
            ->add('idevent', EntityType::class, [
                'class' => 'App\Entity\Event',
                'choice_label' => 'nomevent',
                'attr' => ['class' => 'form-control'],
                'disabled' => True
            ])
            ->add('numticketo', EntityType::class, [
                'class' => 'App\Entity\Ticketo',
                'choice_label' => 'logement',
                'attr' => ['class' => 'form-control'],
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Ticket::class,
        ]);
    }
}
