---
title: Rastreamento J&T Express Alemanha: códigos JTDE e status
description: Rastreie encomendas J&T Express na Alemanha: formato JTDE de 16 caracteres, entrega a vizinhos, preferências de entrega, fotos e suporte.
slug: rastreamento-jt-express-alemanha
published: 2026-11-24
updated: 2026-11-24
---

A J&T Express Germany (*J&T Express Germany GmbH*, sediada em Frankfurt am Main) é a rede alemã do grupo de encomendas J&T Express e realiza entregas na Alemanha. Se uma loja enviou seu pedido pela J&T na Alemanha, o código de rastreamento começa com `JTDE`: cole-o na caixa acima ou consulte diretamente no [rastreador oficial em jtexpress.de](https://www.jtexpress.de/tracking). O número é informado pela loja no e-mail de envio ou na página do seu pedido.

## Código de rastreamento J&T Express Alemanha: como identificar

As perguntas frequentes da J&T informam que os códigos alemães começam com `JTDE`. O Peek conhece apenas um formato para eles: `JTDE` seguido de 12 dígitos, totalizando 16 caracteres (exemplos inventados):

| Código (inventado) | O que é |
| --- | --- |
| `JTDE482917360514` | `JTDE` e 12 dígitos: encomenda J&T Express Germany. O Peek identifica J&T Express Germany imediatamente. |
| `JTDE48291736051` | Um dígito a menos: não corresponde ao formato da J&T Express Germany, e o Peek não reconhece nenhuma transportadora. Verifique se faltou digitar um algarismo. |

Se um código tiver um dígito a menos ou a mais depois de `JTDE` e nenhuma transportadora responder por ele, a página da encomenda em `peektracker.com` alerta que o código começa como um código da J&T Express Germany mas está com um caractere faltando ou sobrando e pede para conferir com a mensagem de envio da loja.

**Onde encontrar o código.** A J&T informa que o remetente envia o número ao destinatário e que ele pode aparecer na página do pedido da loja, no e-mail de confirmação do pedido, em e-mails ou SMS de despacho, em mensagens anunciando ou confirmando a entrega e na etiqueta colada no pacote. Se você não encontrar o número em nenhum desses locais, a J&T orienta acionar o remetente ou o atendimento ao cliente da plataforma de compras. Veja também [como encontrar o código de rastreamento](guide:find-tracking-number).

O rastreador da J&T em `jtexpress.de` aceita até 10 códigos em uma única busca, separados por espaços ou vírgulas.

**Códigos parecidos de outras redes.** As operações da J&T em outros países mantêm seus próprios rastreadores e prefixos: `JTUK` seguido de 12 dígitos pertence à J&T Express UK, `JTAU` seguido de 12 dígitos pertence à [J&T Express Australia](carrier:j-and-t-au), `JTE` seguido de 12 dígitos pertence à J&T Express Middle East e `JT` com 13 dígitos é usado pela J&T no Sudeste Asiático e na China (J&T Express China). Apenas `JTDE` pertence à rede alemã. Se o rastreador responder `Keine Abfrageergebnisse` (`No tracking records yet` em inglês), ou se o Peek continuar mostrando "Aguardando J&T Express Germany", compare todos os 16 caracteres com o e-mail da loja ou pergunte ao vendedor.

## Significado dos status de rastreamento da J&T Express Alemanha

O rastreador da J&T exibe cada movimentação com horário e uma frase, sem nome de cidade ou unidade operacional. A página abre em alemão; ao trocar o idioma no menu para inglês ou chinês, o site recarrega as leituras no idioma selecionado. O Peek consulta as frases em inglês e exibe estas movimentações habituais na ordem em que costumam surgir:

| Status da J&T | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `The shipment has been successfully picked up and is being prepared for transportation` | A J&T coletou o pacote com o remetente e o prepara para transporte. | "Recebida pela transportadora" |
| `The shipment arrives at the distribution center and is prepared for transport to the next destination.` | Chegou a um centro de distribuição e triagem da J&T. | "Em trânsito" |
| `The shipment is dispatched from the transit center to the next destination` | Saiu do centro de trânsito em direção à próxima parada. | "Em trânsito" |
| `Your package has been loaded and is on its way to you` | Está carregado na van de entrega: saiu para entrega. | "Saiu para entrega" |
| `Delivery failed because the address could not be found` | O motorista não conseguiu localizar o endereço informado. | "Tentativa de entrega" |
| `Delivery failed, customer refused to accept` | O destinatário recusou o recebimento do pacote. | "Tentativa de entrega" |
| `Your parcel has been delivered to the pickup point. Please check your email for details` | O motorista entregou o pacote em um ponto de retirada; a J&T envia um e-mail com os detalhes para coleta. | "Pronta para retirada" |
| `Your package has been successfully delivered.` | Entregue ao destino (a J&T acrescenta a forma de entrega, veja abaixo). | "Entregue" |
| `Returns have begun` | Após uma tentativa de entrega sem sucesso, o pacote começou a voltar ao remetente. | "Devolvida ao remetente" |
| `The shipment has been returned` | Devolução concluída ao remetente. | "Devolvida ao remetente" |

Qualquer frase fora desta tabela segue as regras gerais que o Peek aplica a todas as transportadoras; uma frase que o Peek não reconhece permanece escrita como a J&T publicou e conta como "Em trânsito", a menos que a encomenda já esteja em rota de entrega ou em etapa posterior, caso em que o status anterior é mantido.

A linha de carregamento na van pode aparecer mais de uma vez porque a J&T registra essa frase a cada nova rota de entrega. Já a linha de entrega concluída traz o tipo de entrega dentro de colchetes `【】` e termina com um aviso com o telefone de atendimento ao cliente. O Peek descarta o trecho do telefone e mantém o tipo de entrega, sem colchetes, apenas quando corresponde a um destes seis padrões:

- `Left with neighbor`: entregue a um vizinho;
- `Front Door Porch`: deixado na porta da frente;
- `Front Desk Reception`: entregue na recepção ou portaria;
- `Garden Yard`: deixado no jardim ou quintal;
- `Mailbox`: depositado na caixa de correio;
- `Garage`: deixado na garagem.

Assim, uma encomenda deixada com um vizinho aparece no Peek como `Your package has been successfully delivered. The delivery type is Left with neighbor.`

**Qual vizinho recebeu o pacote?** No rastreador oficial em `jtexpress.de`, essa leitura exibe o botão `Zustelldetails anzeigen` (`Click to view delivery details` na versão em inglês). Clique ali e digite o CEP do endereço de entrega para visualizar o nome do vizinho e o número da casa; após cinco CEPs incorretos, é necessário aguardar antes de tentar novamente. Como o Peek consulta usando somente o número de rastreamento, ele mostra apenas que a encomenda foi deixada com um vizinho, sem nome e sem endereço, e remove qualquer nome ou número de telefone presente nas linhas da J&T.

## Entrega da J&T na Alemanha: vizinhos, locais seguros e fotos

Nos históricos reais acompanhados pelo Peek, o entregador da J&T carrega o pacote para a rota e conclui a entrega no endereço, deposita em um ponto de retirada ou registra o motivo da tentativa falha, sem transferência para outra transportadora alemã: o rastreador da J&T segue o envio até o fim. O motorista que vai até sua porta pode trabalhar para um dos parceiros franqueados da J&T na Alemanha.

**Saída para entrega.** `Your package has been loaded and is on its way to you` indica que o pacote está em rota de entrega naquele dia (nos históricos observados, costuma surgir horas antes da entrega na mesma manhã). Como o rastreador da J&T não fornece janela horária nem data prevista, o Peek também não pode exibi-las.

**Preferências de entrega.** O e-mail de notificação de entrega do seu pacote traz um link para a [página de preferências de entrega da J&T](https://www.jtexpress.de/delivery-preference). Confirme o CEP de destino e escolha um dos métodos disponíveis: assinatura pessoal, entrega a um vizinho (informando o nome e se mora ao lado ou no mesmo andar), recepção, ou entrega sem assinatura na porta da frente, no jardim, na caixa de correio ou na garagem. A J&T afirma que o motorista segue a instrução escolhida para evitar segundas tentativas e entregas incorretas, usando os dados apenas para aquela entrega. Ao selecionar um local sem assinatura, a página avisa que o risco de extravio passa a ser seu depois que o pacote for deixado lá.

**Foto de comprovação de entrega.** O aviso de privacidade de prova de entrega (POD) da J&T informa que o motorista fotografa o pacote ao entregá-lo a você ou deixá-lo no local combinado, enquadrando apenas o pacote e o trecho necessário do ambiente; pessoas capturadas por acaso são borradas automaticamente. Em regra, a J&T exclui a foto seis meses após a entrega, podendo mantê-la até o encerramento de reclamações abertas nesse prazo e compartilhá-la com o remetente para resolver uma disputa.

**Recusa ou endereço não encontrado.** Depois de `Delivery failed, customer refused to accept`, nos históricos conhecidos pelo Peek o entregador carregou o pacote para nova rota na manhã seguinte. Depois de `Delivery failed because the address could not be found`, revise o endereço do pedido para ver se falta número do apartamento ou o nome na campainha e avise a loja imediatamente: o FAQ da J&T orienta falar logo com o remetente ou a plataforma de compras, pois a possibilidade de corrigir o endereço em trânsito depende da localização e das regras do remetente. `Returns have begun` pode surgir após falha de entrega. Veja [como alterar o endereço de entrega](guide:change-delivery-address) e [entrega não realizada](guide:missed-delivery).

**Tentativas e pontos de retirada.** O site alemão da J&T não informa número fixo de tentativas de entrega, prazo de guarda nem horário de operação, tampouco lista lojas parceiras, armários ou depósitos para retirada. Ainda assim, uma das frases conhecidas da J&T indica entrega em ponto de retirada com detalhes enviados por e-mail pela J&T. Em caso de dúvida, fale com a loja ou verifique suas mensagens de entrega.

## Encomenda J&T atrasada, perdida ou devolvida

Como a loja contratou a J&T, em compras online a loja é sempre seu primeiro ponto de contato. Pelo direito alemão (§ 475 do Código Civil, BGB), quando um consumidor compra de uma empresa, o vendedor arca com o risco de perda ou avaria durante o transporte, salvo se o próprio comprador contratou a transportadora sem que o vendedor a tenha indicado antes.

**Sem movimentação.** A J&T não publica prazos de entrega ao destinatário. Quando um pacote em "Recebida pela transportadora" ou "Em trânsito" completa quatro dias sem nova leitura, o Peek destaca a data da última movimentação ("Sem atualizações desde seg., 12 de out.") junto do link "Pedir ajuda a J&T Express Germany". Se continuar parado, fale com a loja ou ligue para a central da J&T com o código. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Ainda sem histórico.** Um número que o rastreador da J&T ainda não conhece aparece como "Aguardando J&T Express Germany". Lojas costumam gerar o código antes da coleta física. Se dois dias depois a J&T continuar sem reconhecer o código e nenhum outro serviço responder por ele, o Peek exibe "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar diariamente (e semanalmente depois de uma semana). Confira o número com a loja e pergunte se o envio foi realmente despachado.

**Consta entregue, mas você não recebeu.** Leia a linha de entrega no Peek: se nomear um local como `Mailbox` ou `Garage`, verifique ali; se mostrar `Left with neighbor`, pergunte aos vizinhos ou consulte o nome no rastreador da J&T com o CEP. O FAQ da J&T também recomenda confirmar com familiares, vizinhos ou funcionários do prédio. Se continuar desaparecido, ligue para a J&T e peça reembolso ou reenvio à loja com seu código. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extravio, avaria ou devolução.** Peça reembolso ou substituição à loja informando seu código. O FAQ da J&T não traz formulário, prazo ou tabela de indenização ao destinatário; na Alemanha, os prazos vêm da lei. Pelo § 438 do Código Comercial alemão (HGB), avarias visíveis devem ser relatadas no máximo na entrega (avisar o motorista basta), e avarias ocultas em até sete dias da entrega por escrito (como um e-mail à J&T; enviar dentro do prazo é suficiente). Sem acordo com a J&T sobre extravio ou avaria, você pode recorrer gratuitamente à câmara de arbitragem postal da Bundesnetzagentur (*Schlichtungsstelle Post*), que aceita pedidos online de destinatários e remetentes; quando encomendas de grandes lojas seguem sob contratos especiais que excluem arbitragem, ainda é possível acionar o atendimento ao consumidor postal (*Verbraucherservice Post*). Veja [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente da J&T Express Alemanha

Os canais oficiais divulgados na [página de contato da J&T Express Germany](https://www.jtexpress.de/contact) são:

- **Telefone:** `+49 6931090650` (`069 31090650` dentro da Alemanha), de segunda a sexta, das `8:00` às `20:00` (fechado em feriados).
- **E-mail:** `de-jtservice@jtexpress.de`.
- **Chat online:** botão "Start Chat" nas páginas em inglês ou "Chat starten" nas páginas em alemão de contato e FAQ.
- **Fotos de entrega e dados pessoais:** `de-dpo@jtexpress.de` (conforme política de privacidade de POD).
- **Escritório e sede:** escritório em *J&T Express Germany GmbH, Am Giener 2, 55268 Nieder-Olm*; endereço registrado no aviso legal em *Wiesenhüttenstraße 11, 60329 Frankfurt am Main*.

Para pedidos feitos em lojas online, comece sempre falando com a loja ou pelo suporte da página do pedido no marketplace. Quando um pacote para de se movimentar, o link "Pedir ajuda a J&T Express Germany" no Peek abre diretamente a página de contato da J&T.

## Rastrear encomendas J&T Express Alemanha com o Peek

O [Peek](/) identifica a J&T Express Germany imediatamente a partir de um código `JTDE` ("Transportadora detectada"), digitado com ou sem espaços e em maiúsculas ou minúsculas, consultando o rastreador oficial apenas pelo código, sem exigir CEP ou conta. Links `www.jtexpress.de/tracking?q=` com o código também funcionam (se trouxerem outro formato, o Peek lê o código como se tivesse sido colado sozinho). Para outros códigos, cole normalmente: o Peek acompanha mais de 4.500 transportadoras. Escolher J&T Express Germany por "Trocar" para outro formato não ajuda, pois o Peek avisa que ela "não emite códigos como este".

O Peek exibe a etapa, cada leitura no seu momento exato, mostrado no horário do seu aparelho (encerrando o histórico em peektracker.com e nos apps para iPhone e Android com "Horários no seu fuso horário:" e o nome do fuso) e a hora da entrega. As frases aparecem no inglês da J&T, mantendo até as 100 leituras mais recentes. Como a J&T não informa local, data prevista nem peso, o Peek não exibe mapa. Na página da encomenda, há um atalho para abrir seu código diretamente no site da J&T Express Germany.

Enquanto a J&T não registra leituras, o Peek mostra "Aguardando J&T Express Germany" (ou solicita revisão após dois dias sem resposta). Quando o rastreador da J&T fica indisponível ou não conhece o número, o Peek consulta serviços de rastreamento e exibe o histórico deles. Se posteriormente todos responderem sem leituras para um pacote que já tinha histórico, o Peek conserva as leituras encontradas e exibe "A transportadora não tem movimentações novas por enquanto. As que já foram encontradas ficam salvas, e o Peek continua verificando."

Enquanto você mantém a encomenda aberta no Peek ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6h locais no dia em que a J&T registra saída para entrega, enquanto o rastreador próprio da J&T responde (pelos serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados); fora dessas situações, verifica de hora em hora. Após dois dias sem novidades (12 horas em "Recebida pela transportadora"), checa de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a J&T nem a loja, alterar o endereço nem agendar nova entrega.

## Perguntas frequentes sobre a J&T Express Alemanha

### A J&T Express Germany é confiável?

Sim. A *J&T Express Germany GmbH*, registrada em Frankfurt am Main, é a subsidiária alemã da J&T Express, grupo fundado na Indonésia em 2015 e listado em Hong Kong. A agência reguladora postal alemã (Bundesnetzagentur) registra a empresa em seu cadastro de prestadores postais desde 1º de setembro de 2026, e todas as frases da tabela acima foram observadas em encomendas reais da J&T na Alemanha.

### Qual vizinho recebeu minha encomenda da J&T?

O rastreador oficial em `jtexpress.de` informa: sob a leitura de entrega, clique em `Zustelldetails anzeigen` (`Click to view delivery details` em inglês) e digite o CEP de entrega para ver o nome e o número da casa do vizinho. O Peek mostra apenas `Left with neighbor`, sem nome nem endereço.

### A J&T Express tem pontos de retirada na Alemanha?

O site alemão da J&T não lista lojas parceiras fixas, mas uma das leituras conhecidas da J&T indica entrega em ponto de retirada com detalhes enviados por e-mail pela empresa. Além disso, a página de preferências permite solicitar entrega a você, a um vizinho, à recepção ou a um local seguro no seu endereço.

### Por que meu código J&T não foi encontrado?

Verifique se ele começa com `JTDE` seguido de 12 dígitos — códigos de outras redes, como `JTUK` no Reino Unido, não pertencem à Alemanha. Se o código estiver correto, a J&T pode ainda não ter coletado o pacote (`Keine Abfrageergebnisse` / `No tracking records yet`, exibido no Peek como "Aguardando J&T Express Germany"). Se após vários dias ainda não houver registro, fale com a loja.

### Como entrar em contato com a J&T Express na Alemanha?

Ligue para `+49 6931090650` (segunda a sexta, das `8:00` às `20:00`), use o chat online em `jtexpress.de` ou envie e-mail para `de-jtservice@jtexpress.de`. Para compras online, fale primeiro com a loja que contratou o frete.

:::sources
- [J&T Express Germany: Track & Trace](https://www.jtexpress.de/tracking) – rastreador, até 10 códigos, idiomas, mensagem sem registros e consulta de vizinho protegida por CEP (5 tentativas)
- [J&T Express Germany: Frequently Asked Questions](https://www.jtexpress.de/faq) – códigos `JTDE`, onde encontrar, entregue não recebido, endereço incorreto e chat online
- [J&T Express Germany: Delivery Preferences](https://www.jtexpress.de/delivery-preference) – link no e-mail de aviso, confirmação de CEP, opções com e sem assinatura e risco em local escolhido
- [J&T Express Germany: Contact Us](https://www.jtexpress.de/contact) – `+49 6931090650`, horários, chat, `de-jtservice@jtexpress.de` e escritório em Nieder-Olm
- [J&T Express Germany: Legal notice](https://www.jtexpress.de/legal-statement) – *J&T Express Germany GmbH*, Frankfurt am Main
- [J&T Express Germany: POD Privacy Policy](https://www.jtexpress.de/pod-policy) – fotos de entrega, retenção por 6 meses, reclamações e `de-dpo@jtexpress.de`
- [J&T Express Germany: Become Our Partner](https://www.jtexpress.de/career) – parceiros franqueados para entrega e coleta em toda a Alemanha
- [J&T Express Germany: About Us](https://www.jtexpress.de/about) – fundação em 2015 na Indonésia e listagem em Hong Kong
- [Gesetze im Internet: § 475 BGB](https://www.gesetze-im-internet.de/bgb/__475.html) – risco do transporte assumido pelo vendedor em compras de consumo
- [Gesetze im Internet: § 438 HGB](https://www.gesetze-im-internet.de/hgb/__438.html) – relato de avaria visível na entrega e avaria oculta em até sete dias por escrito
- [Bundesnetzagentur: Anbieterverzeichnis Post](https://www.bundesnetzagentur.de/DE/Fachthemen/Post/Anbieterverzeichnis/PostAnbieterverzeichnis.html) – cadastro de prestadores postais: *J&T Express Germany GmbH*
- [Bundesnetzagentur: FAQ Schlichtung Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/_FAQ_Schlichtung/faqSchlichtung.html) – arbitragem postal gratuita, formulário online e Verbraucherservice Post
- [Universal Parcel Scraper: J&T Express Germany](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-de) – formato `JTDE`, links, frases de status, tipos de entrega e campos filtrados
- [Universal Parcel Scraper: consulta compartilhada da J&T](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – frases em inglês, horários exatos, as 100 leituras mais recentes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – sem repasse a outra transportadora, serviços de rastreamento como alternativa
:::
