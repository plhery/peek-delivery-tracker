---
title: Rastreamento ECMS Express: códigos, status e quem entrega
description: Rastreie um pacote da ECMS Express (易客满): formato dos códigos de 16 caracteres, significado dos status, quem entrega e contatos.
slug: rastreamento-ecms-express
published: 2026-11-24
updated: 2026-11-24
---

A ECMS Express (易客满, também ECMS Global) é uma empresa internacional de entregas expressas que despacha encomendas para lojas online. Se uma loja enviou seu pedido pela ECMS, o código tem 16 caracteres e começa com `EC`, `ES`, `AP` ou `AX`: cole-o na caixa acima. A ECMS transporta o pacote até o país de destino e cuida do desembaraço aduaneiro, entregando-o depois a uma transportadora local para a etapa final.

## Código de rastreamento ECMS Express: como reconhecer

Os códigos da ECMS têm 16 caracteres: `EC`, `ES`, `AP` ou `AX`, seguidos de três a cinco letras e o restante em dígitos. Com caracteres inventados:

| Código | O que é |
| --- | --- |
| `ECLAX48291057364` | `EC`, três letras e onze dígitos: um código da ECMS. |
| `ECSZXA4829105736` | `EC`, quatro letras e dez dígitos: também ECMS. |
| `ECSZXAB482910573` | `EC`, cinco letras e nove dígitos: também ECMS. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | Os mesmos formatos iniciados em `ES`, `AP` ou `AX`. |
| `ECLAX4829105736` | Apenas 15 caracteres: não segue o padrão da ECMS. Veja se falta um dígito. |

Nenhuma outra transportadora do catálogo do Peek usa esses formatos. Espaços e minúsculas não importam no Peek.

**Onde encontrar.** A ECMS imprime o código no conhecimento da etiqueta do pacote, e a loja o envia no e-mail de despacho ou na página do pedido. Se não conseguir achar, veja [como encontrar o código de rastreamento](guide:find-tracking-number).

**Link de rastreamento.** Um link para o rastreador da ECMS terminado em `?number=` e um código já preenche o campo e abre a verificação por imagem. Outros links da ECMS trazem o código depois de `orderNumber=` ou `trackingno=`. Você pode colar qualquer um deles no Peek: ele encontra o código ECMS no link.

## Status do rastreamento ECMS Express explicados

O rastreador da ECMS pede primeiro uma verificação por imagem ("Security verification"). Depois resume o envio em um de oito rótulos — de Shipment Information Received a Delivered ou Exception — e lista as movimentações da mais recente à mais antiga, com data, hora e local. O Peek mostra cada evento com o texto original da ECMS. Na ordem em que o pacote os recebe:

| A ECMS mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | A loja enviou os dados do pacote à ECMS. A ECMS ainda não está com ele. | "Etiqueta criada" |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | A ECMS recebeu os documentos aduaneiros ou validou o documento do destinatário. Ainda não é leitura física do pacote. | "Etiqueta criada" |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | O armazém de origem da ECMS recebeu o pacote: primeira leitura real. | "Recebida pela transportadora" |
| `Parcel has arrived at export distribution warehouse` | No armazém de exportação da ECMS, no mesmo dia da primeira leitura. | "Em trânsito" |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | Saiu daquele armazém. | "Em trânsito" |
| `Flight departure`, `Flight arrival` | O voo decolou e depois pousou. | "Em trânsito" |
| `Arriving notification received from terminal` | O terminal de cargas do aeroporto confirmou a chegada da carga do voo. | "Em trânsito" |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Armazém da ECMS no aeroporto de chegada: entra antes da alfândega de importação e sai após a liberação. | "Em trânsito" |
| `Shipment started customs clearance process.` | Em desembaraço na alfândega do país de destino. | "Na alfândega" |
| `Shipment completed customs clearance process.` | A alfândega de importação liberou o pacote. | "Em trânsito" |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | Na unidade do país de destino, onde é pesado e aguarda a transportadora local. | "Em trânsito" |
| `Shipment is tendering to third party carrier for final delivery.` | A ECMS está repassando o pacote à transportadora local. O número dela pode aparecer a partir daqui. | "Em trânsito" |
| `Return to origin` | Um problema na unidade de destino, nem sempre devolução real: em um histórico no código do Peek, a transportadora local coletou no dia seguinte e a entrega seguiu. | "Precisa de atenção" |
| `Local carrier has accepted package for final delivery` | A transportadora local recebeu o pacote. | "Em trânsito" |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Circulando pela rede da transportadora local, conforme repassado pela ECMS. A segunda frase pode se repetir nos dias seguintes. | "Em trânsito" |
| `Shipment is out for delivery.` | Na rota de entrega. | "Saiu para entrega" |
| `Available for pickup` | A transportadora local guarda o pacote para você retirar. | "Pronta para retirada" |
| `Shipment delivered to customer` | Entregue. | "Entregue" |

**Em chinês.** Alguns históricos trazem essas etapas em chinês, e o Peek atribui as mesmas fases das versões em inglês: `发货预报已收到，正在等待货物到达我们的始发站仓库` (dados recebidos, aguardando pacote), `干线航班起飞` e `干线航班已到达` (voo partiu e pousou), `货物已进入清关流程` (na alfândega), `货物已清关放行` (liberado), `货物已到达目的站仓库` (no armazém de destino) e `货物出库前往配送中心安排最终派送` (a caminho da transportadora local).

**Horários.** A ECMS marca suas próprias leituras com o relógio e deslocamento UTC da estação onde ocorreram, e o Peek lê dessa forma. Eventos da transportadora local repassados pela ECMS vêm com deslocamento zero: o Peek lê como UTC, a menos que uma leitura desse fluxo fique, assim, mais de dez minutos depois do registro na ECMS — nesse caso, o Peek lê os horários desse fluxo como horários locais do lugar informado. Linhas de voo (`Flight departure`, `Flight arrival`) marcadas exatamente à meia-noite indicam apenas o dia do voo e aparecem no Peek sem hora. Veja também [como rastrear encomendas da China](guide:tracking-from-china).

## Quem entrega pacotes da ECMS Express

Para o trecho final, a ECMS diz trabalhar com transportadoras locais confiáveis ("trusted local carriers"). O rastreador dela pode mostrar essa empresa no quadro "Last Mile Delivery" como "LM Provider", junto com o código dela em "LM Tracking No." e um link "Track with …". Nos históricos do código do Peek, a entregadora local nos EUA é a [SpeedX](carrier:speedx), cujas leituras a ECMS repassa como `Parcel in transit during last mile delivery`.

**Prazo de entrega.** A ECMS diz que varia conforme origem, destino e serviço. Saindo dos Estados Unidos, a página do serviço informa de 3 a 6 dias úteis para o Reino Unido, 3 a 11 para o Canadá, 3 a 8 para a Austrália e 4 a 8 para a União Europeia.

**Tentativas de entrega e retirada** seguem as regras da transportadora local, portanto consulte-a: a ECMS não publica regras para destinatários. Veja [entrega não realizada](guide:missed-delivery).

## Pacote ECMS Express atrasado ou rastreamento parado

**"We could not find a shipment for this tracking number."** O rastreador da ECMS não conhece o número: erro de digitação ou a loja ainda não enviou os dados. O Peek mostra "Aguardando ECMS Express" e continua verificando. Confira o número; se nada surgir após alguns dias, confirme com o vendedor se o envio foi feito. Se depois de dois dias a ECMS continuar sem reconhecer e nenhum serviço de rastreamento encontrar, o Peek exibe: "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda."

**Parado em `Virtual Manifest has been received`.** A ECMS tem apenas os dados, não o pacote. Pergunte à loja quando ela entregou o pacote à ECMS.

**Parado na alfândega.** Após `Shipment started customs clearance process.`, o pacote aguarda a alfândega do seu país. O FAQ da ECMS explica que em envios DDU ou DAP o destinatário pode precisar pagar impostos e tarifas antes da entrega; em envios DDP, o remetente costuma pagar antecipadamente. Se pedirem pagamento, veja [encomendas paradas na alfândega](guide:customs).

**Nada após `Shipment is tendering to third party carrier for final delivery.`.** A transportadora local já recebeu o pacote ou vai recebê-lo em breve, e o rastreamento dela pode estar mais adiantado. Procure o nome e o número no quadro "Last Mile Delivery" da ECMS e, se o Peek ainda não estiver acompanhando por lá, adicione esse código ao Peek também. Mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não chegou.** Pergunte à transportadora local onde deixou o pacote e avise o vendedor. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extraviado, avariado ou item errado.** O FAQ da ECMS direciona você à loja ou vendedor para substituição ou reembolso. Apenas o expedidor pode abrir reclamação na ECMS, por escrito em até 30 dias após a ECMS aceitar o pacote, então avise a loja rápido. Veja [encomendas extraviadas](guide:lost-parcel).

**Devolvido.** Entre os motivos citados pela ECMS estão itens proibidos ou restritos, dados de envio errados ou incompletos, problemas alfandegários, tributos não pagos e destinatário que não conclui os trâmites da alfândega. Uma linha `Return to origin` sozinha não prova devolução: se não houver novas leituras da transportadora local em alguns dias, fale com o vendedor. Veja [devolvida ao remetente](guide:returned-to-sender).

**Cancelar ou alterar o pedido.** A ECMS não faz isso por ser apenas a transportadora: fale com o vendedor.

## Telefone e atendimento da ECMS Express

A página Contact Us da ECMS lista estes telefones para países de língua inglesa, sem especificar o fuso horário (Canadá e Irlanda não têm entrada própria):

- **Estados Unidos:** +1 626 956 5388, de segunda a sexta-feira, das 9h às 18h.
- **Reino Unido:** 01753 528888, de segunda a sexta-feira, das 8h30 às 17h.
- **Austrália:** +61 2 8317 6262, de segunda a sexta-feira das 11h às 20h, sábado das 11h às 15h.
- **Formulário de mensagem:** escolha "Shipment Support" como assunto e informe o código de rastreamento. Cada região também tem seu próprio e-mail.

## Como rastrear um pacote da ECMS Express no Peek

O [Peek](/) identifica códigos da ECMS pelo formato e consulta diretamente o rastreador da ECMS. Não precisa de conta, CEP nem telefone.

A verificação por imagem da ECMS é uma soma simples: o próprio Peek a lê e, se falhar, tenta mais uma vez com nova imagem. Se a ECMS ainda assim não responder, o Peek recorre a serviços de rastreamento nesse meio tempo e mostra o histórico deles, nas palavras deles.

O Peek exibe o status, cada movimentação com cidade, região e país e o país de destino, do mais recente ao mais antigo, com horários no fuso do seu aparelho; em peektracker.com e nos aplicativos para iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele. Ele omite referências da loja e links de pagamento presentes no registro da ECMS, e não exibe peso nem data prevista de entrega.

Quando o registro da ECMS informa uma transportadora local conhecida pelo Peek, ele mostra "Entrega por" seguido dela (e do número dela quando aparece nas leituras). Enquanto a entregadora só tiver a pré-notificação, sem leitura própria, aparece "Anunciada a …, ainda não recebida". Se o Peek lê o rastreador direto dela, consulta-o também e migra para ele assim que ela mostrar uma leitura pelo menos tão recente quanto a última da ECMS, mantendo as leituras da ECMS. Caso contrário, adicione o "LM Tracking No." como outro pacote: o Peek acompanha mais de 4.500 transportadoras.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia da saída para entrega, enquanto o rastreador da ECMS responder; fora disso, de hora em hora. Apenas por serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia em que sai para entrega enquanto o pacote estiver aberto ou com alertas ligados. Quando passa a acompanhar na transportadora local, segue o ritmo dela. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode receber alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". "Etapas importantes" inclui a etapa aduaneira da ECMS, `Return to origin`, saída para entrega, `Available for pickup` e entrega — não voos nem passagens por armazém. O Peek não pode falar com a ECMS nem com o vendedor por você.

## Perguntas sobre o rastreamento ECMS Express

### O que é a ECMS Express (易客满)?

É uma empresa internacional de entregas expressas e logística fundada em 2013, com filiais próprias na China continental, Hong Kong, Singapura, EUA, Reino Unido, Europa, Coreia do Sul e Japão. Seu site chinês indica a razão social 天津易客满国际物流有限公司.

### Onde posso rastrear um pacote da ECMS Express?

Na caixa acima ou na página "Track Your Shipment" em ecmsglobal.com, após passar pela verificação por imagem.

### Quem entrega pacotes da ECMS Express no meu país?

Uma transportadora local com a qual a ECMS trabalha, que pode aparecer em "LM Provider" no rastreador da ECMS. Nos históricos presentes no código do Peek, é a SpeedX nos EUA; em outros países, confira esse quadro ou pergunte ao vendedor.

### O que significa "Shipment is tendering to third party carrier for final delivery"?

Que a ECMS está repassando seu pacote à transportadora local que fará a entrega, cujo número pode então aparecer em "LM Tracking No." no rastreador da ECMS. O Peek exibe "Em trânsito".

### Quem devo contatar se meu pacote da ECMS Express sumir?

Primeiro o vendedor: o FAQ da ECMS encaminha destinatários ao varejista para reembolso ou substituição, e só o expedidor pode reclamar junto à ECMS. Sobre a entrega em si, fale com a transportadora local; a ECMS lista telefones nos EUA, Reino Unido e Austrália.

:::sources
- [ECMS Express: Home](https://www.ecmsglobal.com/en-us/home.html) – serviço expresso para lojas online
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – fundada em 2013
- [易客满: 关于我们 (sobre nós)](https://www.ecmsglobal.com/zh-cn/about.html) – 2013, filiais próprias e razão social
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – transportadoras locais na última milha e prazos a partir dos EUA
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – verificação por imagem, resumos, leituras, quadro "Last Mile Delivery", mensagem de não encontrado e link
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – conhecimento, impostos e taxas, devoluções, cancelamento, extravio e prazo de 30 dias para reclamação pelo expedidor
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – telefones regionais, horários e formulário
- [ECMS Express: Support (UK)](https://www.ecmsglobal.com/en-uk/support.html) – telefone do Reino Unido em formato local
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formatos de código, links, verificação por imagem e nova tentativa, textos dos eventos em inglês e chinês e suas fases, horários, locais, a transportadora local e o número dela, o que o Peek omite, serviços de rastreamento
:::
