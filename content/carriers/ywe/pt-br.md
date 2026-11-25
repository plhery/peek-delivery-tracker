---
title: Rastreamento YWE (YanwenExpress): códigos, status e suporte
description: Rastreie um pacote da YWE (YanwenExpress) nos Estados Unidos: formato do código YWE, significado de cada status da triagem à entrega e contato.
slug: rastreamento-ywe
published: 2026-11-24
updated: 2026-11-24
---

A YWE (YanwenExpress, operada pela Yanwen Express LLC) é uma transportadora de última milha nos Estados Unidos: ela leva encomendas de seus centros de distribuição até a sua porta para lojas online, varejistas e marketplaces. Seus códigos têm `YWE` e 14 dígitos: cole o seu na caixa acima ou no rastreador da YWE em [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## Código de rastreamento YWE: como reconhecer

Os códigos da YWE são compostos por `YWE` seguido de 14 dígitos, totalizando 17 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `YWE48291057364911` | `YWE` e 14 dígitos: um código da YWE. |
| `YWE4829105736491` | Apenas 13 dígitos: não segue o padrão da YWE. Veja se falta um dígito; se nenhuma transportadora responder por ele, a página em peektracker.com avisa "Começa como um código de rastreamento de YWE, mas falta um caractere: pode haver um erro de digitação." |
| `YWLAX482910573649` | `YW`, três letras e 12 dígitos: código da [Yanwen](carrier:yanwen), não da YWE. |

Espaços e letras minúsculas não importam ao colar o código no Peek.

**Onde encontrar.** No e-mail de envio da loja ou na página do seu pedido. Se não encontrar, veja [como encontrar o código de rastreamento](guide:find-tracking-number).

**Link de rastreamento.** Um link para o rastreador da YWE terminado em `?label=` e um código abre aquele pacote diretamente. Você pode colar o link inteiro no Peek. O rastreador da YWE aceita até 30 códigos de uma vez.

## Status do rastreamento YWE explicados

O rastreador da YWE lista cada movimentação com data, hora, deslocamento em relação ao GMT, cidade e estado, do mais recente ao mais antigo. Os registros estão em inglês. Na ordem em que o pacote os recebe:

| A YWE mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipping Order Created.` | O remetente reservou a entrega com a YWE. A YWE ainda não está com o pacote. | "Etiqueta criada" |
| `Container picked up by YWE,LAX01 Awaiting Item.` | A YWE recolheu o contêiner em que seu pacote voou. O pacote em si ainda não foi bipado. | "Em trânsito" |
| `Container received` | O centro deu entrada no contêiner. | "Em trânsito" |
| `Parcel received by in LAX01.` | Primeiro registro individual do pacote no centro da YWE. `Parcel received by NJC01.`, sem "in", é o mesmo evento em outro centro. | "Em trânsito" |
| `Parcel departed from Facility.` | Saiu do centro de distribuição. | "Em trânsito" |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `Parcel departed from Transit Facility.`, `In transit to next facility.` | Em um centro intermediário no caminho, antes da estação que atende sua região. | "Em trânsito" |
| `Parcel received by local  Facility.` | Chegou à estação de entrega local responsável pelo seu endereço. | "Em trânsito" |
| `Parcel is out for delivery.` | Na rota de entrega. | "Saiu para entrega" |
| `Parcel has been delivered.(Front door)` | Entregue. Os parênteses indicam onde o pacote foi deixado. | "Entregue" |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | A entrega falhou pelo motivo entre colchetes. O pacote volta ao depósito da YWE, não ao remetente. | "Tentativa de entrega" |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | O mesmo, descrito como falha: volta ao depósito da YWE, de onde pode sair novamente para entrega. | "Tentativa de entrega" |
| `Return to sender` | O pacote está voltando ao remetente, mas ainda não chegou lá. | "Precisa de atenção" |
| `Returned to sender, shipment has been received in sender warehouse.` | Já chegou de volta ao depósito do remetente. | "Devolvida ao remetente" |

**Códigos, motivos e locais.** `LAX01` e `NJC01` são códigos de centros: passagens por outros centros trazem o código respectivo. Da mesma forma, `[Restricted Address]` é apenas um motivo de falha e `(Front door)` um dos locais possíveis de entrega; outros registros indicam outros motivos e locais.

**Alert nem sempre significa problema.** O rastreador da YWE agrupa pacotes em Awaiting, Transit, Delivered, Alert e Returned conforme o último evento. Ele classifica `Parcel received by local  Facility.` — o recebimento na estação local — sob Alert, embora seja uma etapa normal. O Peek mostra esse evento como "Em trânsito".

**Outros textos.** Qualquer movimentação fora da tabela aparece no Peek exatamente como a YWE escreveu, e o Peek identifica a etapa pelo texto sempre que possível.

**Horários.** Cada registro traz o deslocamento do centro em relação ao GMT. O Peek lê os horários assim e exibe todos no fuso horário do seu aparelho; em peektracker.com e nos aplicativos para iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele.

## Quem entrega pacotes da YWE

**Estados Unidos.** A própria YWE opera a última milha, por meio do que seu site chama de "Delivery Service Partner networks nationwide": equipes locais de entrega em qualquer ponto dos Estados Unidos que integram sua rede para entregar ou coletar pacotes. No código do Peek, o histórico da YWE vai do centro da YWE até sua porta e não menciona nenhuma outra transportadora.

A YWE afirma ter começado em Los Angeles em 2024 e planeja cobrir a maior parte dos Estados Unidos até o fim de 2026.

**Tentativas de entrega e retirada.** A YWE não publica regras sobre quantas tentativas faz, se deixa aviso ou se oferece pontos de retirada. Depois de uma tentativa sem sucesso, o registro indica que o pacote volta ao depósito da YWE. Para combinar nova entrega, envie um e-mail à YWE (veja abaixo) com o código de rastreamento, seu endereço completo e instruções de entrega. Veja [entrega não realizada](guide:missed-delivery).

**Reino Unido, Irlanda, Canadá e Austrália.** A YWE não cita atendimento fora dos Estados Unidos. Se você recebeu um código YWE para um envio destinado a outro país, pergunte ao vendedor qual transportadora faz a entrega local.

**YWE e Yanwen.** A [Yanwen](carrier:yanwen) é outra transportadora no Peek, com códigos e rastreador próprios, e o site da YWE não informa se há vínculo entre as duas. Quando o rastreador da Yanwen aponta a YanwenExpress como distribuidora do pacote e informa um código YWE, o Peek exibe "Entrega por YWE" e passa a consultar o rastreador da YWE também. Enquanto a YWE tem apenas o aviso prévio, sem movimentação própria, o Peek mostra "Anunciada a YWE, ainda não recebida". Assim que a YWE registra um evento pelo menos tão recente quanto o último da Yanwen, o Peek acompanha o pacote no rastreador da YWE mantendo os eventos anteriores da Yanwen.

## Pacote YWE atrasado ou rastreamento parado

**`NOT_FOUND`.** O rastreador da YWE mostra esse aviso quando não há registros para o número, citando causas possíveis: o pacote ainda não foi aceito ou bipado, o código está errado ou foi postado há tanto tempo que o histórico expirou. O Peek exibe "Aguardando YWE" e segue tentando. Confira o código na mensagem do vendedor: `YWE` e 14 dígitos. Se nada surgir após alguns dias, confirme com o vendedor se o pacote foi enviado.

**Parado em `Shipping Order Created.`.** A YWE recebeu os dados da entrega, mas não o pacote. A primeira leitura da YWE acontece no centro dos Estados Unidos, então pergunte ao vendedor onde o envio está e quando foi despachado. Se veio da China, veja [como rastrear encomendas da China](guide:tracking-from-china).

**Parado em `Container picked up by YWE,LAX01 Awaiting Item.`.** A YWE já retirou o contêiner, mas ainda não bipou seu pacote individualmente.

**`Parcel delivery abnormal` ou `Parcel delivery failed`.** A entrega não ocorreu pelo motivo entre colchetes e o pacote retorna ao depósito da YWE. Envie um e-mail à YWE com o número, endereço completo e instruções de entrega, e avise o vendedor.

**`Return to sender`.** O pacote está voltando. Quando aparecer `Returned to sender, shipment has been received in sender warehouse.`, o remetente já o recebeu. Peça reembolso ou novo envio ao vendedor. Veja [encomenda devolvida ao remetente](guide:returned-to-sender).

**Entregue, mas nada chegou.** Os parênteses no evento de entrega, como `(Front door)`, dizem onde o pacote foi deixado. Em pacotes entregues, o rastreador da YWE oferece um link "view receipt": digite seu ZIP code no campo "Recipient's postal code" para ver as fotos da entrega, quando existirem. Depois envie um e-mail à YWE e avise o vendedor. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extraviado.** As contas da YWE são exclusivas para empresas, portanto o cliente da YWE é o negócio que contratou o frete, não o destinatário. Peça reembolso ou reenvio ao vendedor ou ao marketplace. O site da YWE não informa processo de indenização para destinatários. Veja [encomenda extraviada](guide:lost-parcel) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).

## Telefone e atendimento da YWE

O site da YWE, em inglês, informa os seguintes canais:

- **E-mail:** um endereço no rodapé de todas as páginas. Ao clicar, o site mostra o que enviar: sua dúvida, o código de rastreamento e, no corpo da mensagem, endereço completo, instruções de entrega, telefone, e-mail e o pedido que você está fazendo.
- **Telefone:** nenhum. A página Customer support diz "Calling us for further enquiries", mas não traz número nem horário de atendimento.
- **Perguntas frequentes (FAQs):** na página Customer support, voltadas para parceiros de entrega e não para quem aguarda uma encomenda.

Para questões do pedido, procure o vendedor. Para a entrega em si, mande e-mail para a YWE.

## Como rastrear um pacote da YWE no Peek

O [Peek](/) identifica códigos da YWE pelo formato — `YWE` e 14 dígitos, exclusivo dela no catálogo — e consulta diretamente o rastreador da YWE. Links do rastreador da YWE também funcionam. Não exige conta, CEP nem telefone.

O Peek mostra o status, cada registro com cidade, estado e país e o horário da entrega. Registros repetidos são eliminados, assim como o nome de quem recebeu quando um evento de entrega traz esse dado.

O rastreador da YWE também pode mostrar uma janela de entrega de duas horas durante o trajeto, um mapa com a posição do motorista e as fotos de entrega. O Peek omite esses itens e o endereço de rua das movimentações, e não mostra data prevista de entrega.

Como o rastreador da YWE não cita outra transportadora, ao adicionar um código YWE o Peek não exibe a linha "Entrega por" e acompanha na YWE até a entrega. Se o rastreador da YWE ficar indisponível, o Peek consulta serviços de rastreamento nesse intervalo.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia em que o pacote sai para entrega, enquanto o rastreador da YWE responder (por meio de serviços de rastreamento, até a cada 15 minutos nesse dia, enquanto o pacote estiver aberto ou com alertas ligados); fora disso, de hora em hora. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Ele pode enviar alertas para "Cada movimentação", "Etapas importantes" (entrega, retirada, alfândega, tentativas sem sucesso e devoluções) ou "Só a entrega". Não pode falar com a YWE nem com o vendedor por você.

## Perguntas sobre o rastreamento YWE

### O que é a YWE (YanwenExpress)?

É uma transportadora de última milha nos Estados Unidos, operada pela Yanwen Express LLC. Ela entrega encomendas para lojas online, varejistas e marketplaces por meio de uma rede de equipes locais e afirma ter começado em Los Angeles em 2024.

### A YWE é a mesma empresa que a Yanwen?

O Peek trata as duas como transportadoras separadas: a Yanwen tem códigos e rastreador próprios, e o site da YWE não informa se há ligação entre elas. Quando o rastreador da Yanwen cita a YanwenExpress como distribuidora e informa um código YWE, o Peek acompanha esse número também no rastreador da YWE.

### Onde posso rastrear um pacote da YWE?

Na caixa acima ou no rastreador da YWE em yanwenexpress.com, que aceita até 30 códigos por vez. Digite o número completo: `YWE` e 14 dígitos.

### O que significa "Container picked up by YWE"?

Que a YWE retirou o contêiner em que seu pacote chegou de avião. O código após a vírgula, como LAX01, identifica o centro. A leitura do seu pacote em si, `Parcel received by in LAX01.`, aparece depois. O Peek mostra "Em trânsito" para ambos.

### Quem devo contatar se meu pacote da YWE sumir?

Primeiro o vendedor, pois foi ele quem contratou a entrega com a YWE. A YWE recebe dúvidas pelo e-mail no rodapé do site, informando o código de rastreamento; não há telefone publicado.

:::sources
- [YanwenExpress: home page](https://www.yanwenexpress.com/) – última milha nos Estados Unidos via redes Delivery Service Partner, 30 códigos por vez, e-mail no rodapé e dados a informar
- [YanwenExpress: About us](https://www.yanwenexpress.com/about.html) – Los Angeles em 2024, cobertura da maior parte dos EUA até o fim de 2026
- [YanwenExpress: Tracking](https://www.yanwenexpress.com/tracking.html) – registros com deslocamento GMT, cidade e estado, categorias Awaiting, Transit, Delivered, Alert e Returned, NOT_FOUND e causas possíveis, janela de entrega, mapa do motorista, "view receipt" com código postal do destinatário e 30 códigos
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – sem telefone nem horário exibidos, FAQs para parceiros de entrega
- [YanwenExpress: Join us](https://www.yanwenexpress.com/joinus.html) – equipes locais nos Estados Unidos integrando a rede
- [YanwenExpress: Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC, contas exclusivas para empresas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formato do código YWE, o rastreador e seu link, textos dos registros e suas etapas, códigos de centros, motivos e locais, a classificação em Alert, deslocamentos em relação ao GMT, locais e nomes que o Peek mantém ou omite, o que o Peek deixa de fora, nenhuma outra transportadora no histórico da YWE, a passagem da Yanwen para a YWE quando ela cita a YanwenExpress
:::
