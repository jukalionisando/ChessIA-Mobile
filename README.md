# ChessIA Mobile Advanced 2.0

Projeto Android de análise de xadrez e tabuleiro customizável.

## Recursos nesta versão-fonte
- Tabuleiro 8x8 e movimentação por toque.
- Modo de edição de peças (colocar/apagar peças).
- Escolha de lado a jogar, padrão Brancas.
- Histórico de lances com voltar/avançar.
- Exportação/carga de FEN (copiar e colar).
- Ajuste de profundidade e quantidade de linhas exibidas na interface.
- Interface de análise Stockfish carregada via CDN no WebView.
- Bolha de sobreposição Android para abrir o app rapidamente.

## Limitações importantes
Esta é uma base avançada de projeto, ainda não um APK validado. O Stockfish nesta versão depende de conexão e de compatibilidade do worker externo com WebView. Não possui Stockfish embarcado/offline, captura automática de posição do Chess.com, setas renderizadas sobre a posição, nem análise persistente garantida ao suspender o processo Android. A bolha de sobreposição abre o aplicativo; ela não captura nem lê o tabuleiro de outro app.

## Gerar APK
1. Instale Android Studio e JDK 17.
2. Abra esta pasta no Android Studio.
3. Sincronize o Gradle e instale Android SDK 35 se solicitado.
4. Vá a Build > Build APK(s).
5. APK debug: `app/build/outputs/apk/debug/app-debug.apk`.

Para distribuir: gere um APK assinado pelo menu Build > Generate Signed Bundle / APK.
