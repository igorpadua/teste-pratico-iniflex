import modal.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - inserindo os funcionarios
        funcionarios.add(new Funcionario(
                "Maria",
                LocalDate.of(2000, 10, 18),
                "Operador",
                new BigDecimal("2009.44")
        ));

        funcionarios.add(new Funcionario(
                "João",
                LocalDate.of(1990, 12, 5),
                "Operador",
                new BigDecimal("2294.38")
        ));

        funcionarios.add(new Funcionario(
                "Caio",
                LocalDate.of(1961, 5, 2),
                "Coordenador",
                new BigDecimal("9836.14")
        ));

        funcionarios.add(new Funcionario(
                "Miguel",
                LocalDate.of(1988, 10, 2),
                "Diretor",
                new BigDecimal("19119.88")
        ));

        funcionarios.add(new Funcionario(
                "Alice",
                LocalDate.of(1995, 1, 5),
                "Recepcionista",
                new BigDecimal("2234.68")
        ));

        funcionarios.add(new Funcionario(
                "Heitor",
                LocalDate.of(1999, 11, 19),
                "Operador",
                new BigDecimal("1582.72")
        ));

        funcionarios.add(new Funcionario(
                "Arthur",
                LocalDate.of(1993, 3, 31),
                "Contador",
                new BigDecimal("4071.84")
        ));

        funcionarios.add(new Funcionario(
                "Laura",
                LocalDate.of(1994, 7, 8),
                "Gerente",
                new BigDecimal("3017.45")
        ));

        funcionarios.add(new Funcionario(
                "Heloísa",
                LocalDate.of(2003, 5, 24),
                "Eletricista",
                new BigDecimal("1606.85")
        ));

        funcionarios.add(new Funcionario(
                "Helena",
                LocalDate.of(1996, 9, 2),
                "Gerente",
                new BigDecimal("2799.93")
        ));

        // 3.2 - removendo o funcionario João
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imrprimindo os funcionarios
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumentando o salario dos funcionarios em 10%
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.1"))));

        // 3.5 - Agrupar os funcionarios por funcao
        Map<String, List<Funcionario>> agurpandoPorFuncao = funcionarios
                .stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimindo os funcionarios agrupados por funcao
        System.out.println("\nFuncionarios agrupados por funcao");
        agurpandoPorFuncao.forEach((funcao, funcionariosDaFuncao) -> {
            System.out.println("Funcionarios da funcao " + funcao);
            funcionariosDaFuncao.forEach(System.out::println);
        });

        // 3.8 - Imprimir funcionarios que fazer aniverśario no mes 10 e 12
        System.out.println("\nFuncionarios que fazem aniversario em outubro e dezembro");
        funcionarios
                .stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade
        System.out.println("\nFuncionario com a maior idade");
        funcionarios
                .stream()
                .min((funcionario1, funcionario2) -> funcionario1.getDataNascimento().compareTo(funcionario2.getDataNascimento()))
                .ifPresent(funcionario -> {
                    LocalDate dataAtual = LocalDate.now();
                    int idade = dataAtual.getYear() - funcionario.getDataNascimento().getYear();
                    System.out.println("Nome: " + funcionario.getNome() + " - idade: " + idade);
                });

        // 3.10 - Imprimir a lista de funcionarios ordenada por nome
        System.out.println("\nFuncionarios ordenados por nome");
        funcionarios
                .stream()
                .sorted((funcionario1, funcionario2) -> funcionario1.getNome().compareTo(funcionario2.getNome()))
                .forEach(System.out::println);

        // 3.11 - Imprimir total dos salarios
        System.out.print("\nTotal dos salarios: ");
        BigDecimal totalSalarios = funcionarios
                .stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(totalSalarios);

        // 3.12 - Imprimir quantos solarios minimos ganha cada funcionario, salario minimo = 1212.00
        System.out.println("\nQuantos salarios minimos ganha cada funcionario:");
        funcionarios
                .forEach(funcionario -> {
                    BigDecimal salarioMinimo = new BigDecimal("1212.00");
                    BigDecimal quantosSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 1, RoundingMode.DOWN);
                    System.out.println(funcionario.getNome() + " - " + quantosSalariosMinimos + " salarios minimos");
                });
    }
}