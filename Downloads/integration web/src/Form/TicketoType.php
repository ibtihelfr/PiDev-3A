<?php

namespace App\Form;

use App\Entity\Ticketo;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TicketoType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('logement', ChoiceType::class, [
                'attr' => ['class' => 'form-control'],
                'choices' => [
                    "El Mouradi" => "El Mouradi",
                    "Thalasso" => "Thalasso",
                    "Iberostar" => "Iberostar"]
            ])
            ->add('restauration', ChoiceType::class, [
                'attr' => ['class' => 'form-control'],
                'choices' => [
                    "Lorenzia" => "Lorenzia",
                    "Golf la Marsa" => "Golf la Marsa",
                    "Dar Zarrouk" => "Dar Zarrouk"]
            ])
            ->add('prix', NumberType::class, ['attr' => ['class' => 'form-control']]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Ticketo::class,
        ]);
    }
}
