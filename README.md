
# UFRN
Trabalho final da primeira unidade da disciplina de DESENVOLVIMENTO DE SISTEMAS PARA DISPOSITIVOS MÓVEIS - UFRN/IMD
## Alunos
- Breno Jalmir de Medeiros Almeida
- Felipe Madureira de Almeida

# BasketHub
BasketHub é um aplicativo Android focado em basquete, que reúne em um só lugar um placar de jogo, calculadora, histórico de partidas e cálculo de AST%.

## Funcionalidades

- **SlamScore (Placar)**
  - Controle de pontuação para dois times (3 pontos, 2 pontos e lance livre).
  - Cronômetro de período com contagem regressiva.
  - Salvamento de partidas em histórico local.

- **SlamCalc (Calculadora)**
  - Calculadora numérica com operações básicas e científicas.
  - Acesso rápido ao cálculo de AST% a partir da calculadora.

- **SlamMatch (Histórico de Partidas)**
  - Lista das partidas salvas localmente.
  - Registra partidas manualmente.
  - Exibição de times, placar, data/hora e quantidade de quartos.

- **Tema Claro/Escuro (DayNight)**
  - Suporte a tema claro e escuro usando `Theme.Material3.DayNight`.
  - Botão "Alternar tema" na tela Hub.
  - O modo escuro é aplicado como padrão na primeira abertura do app; a escolha do usuário é persistida e reutilizada nas próximas execuções.

## Arquitetura

- Aplicativo Android nativo em **Kotlin**.
- Padrão de navegação simples via `Activity` + `Intent`.
- Layouts construídos com XML em `app/src/main/res/layout`.
- Recursos centralizados em:
  - `app/src/main/res/values/colors.xml` e `values-night/colors.xml` para cores.
  - `app/src/main/res/values/strings.xml` para textos.
  - `app/src/main/res/values/dimens.xml` para dimensões.
- Persistência simples em arquivo JSON local (`matches.json` em `filesDir`).

## Principais Activities

- `AppHub` — Tela principal (hub) de navegação e alternância de tema.
- `ScoreboardApp` — Placar de jogo (SlamScore).
- `CalculatorApp` — Calculadora (SlamCalc) com atalho para AST%.
- `GamesHistoryApp` — Histórico de partidas (SlamMatch).
- `AddMatchActivity` — Adição manual de partida.
- `AstActivity` — Cálculo de AST%.

## Requisitos

- **Android Studio** (Flamingo ou superior recomendado).
- **Gradle** gerenciado pelo Android Studio.
- SDK Android configurado para a API alvo definida em `app/build.gradle.kts`.

## Como executar

1. Clonar o repositório:

   ```bash
   git clone https://github.com/SEU_USUARIO/BasketHub.git
   cd BasketHub
   ```

2. Abrir o projeto no Android Studio.
3. Aguardar a sincronização do Gradle.
4. Escolher um dispositivo físico ou emulador (Recomendamos o Pixel 6).
5. Executar a aplicação (botão **Run** ou `Shift+F10`).


