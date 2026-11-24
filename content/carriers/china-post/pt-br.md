---
title: Rastreamento China Post: códigos CN, status e entrega
description: Rastreie encomendas da China Post no Brasil: formato dos códigos CN, significado dos status em chinês, entrega pelos Correios e alfândega.
slug: rastreamento-china-post
published: 2026-11-24
updated: 2026-11-24
---

A China Post é o serviço postal nacional estatal da China, e a EMS é sua marca de remessa expressa. Se sua compra no AliExpress, Temu, Shein ou em outra loja veio com um código de rastreamento terminado em `CN`, ele foi emitido pela China Post, e o serviço postal do país de destino — como os [Correios](carrier:correios-br) no Brasil — entrega o pacote sob esse mesmo código. Você pode acompanhar a remessa no site da China Post (`ems.com.cn`) ou colando o código na caixa acima. O número aparece nos detalhes de envio do pedido enviados pelo vendedor.

## Código de rastreamento China Post: como identificar

Um código padrão da China Post tem duas letras, nove dígitos e `CN` no final: é o formato postal internacional S10 definido pela União Postal Universal (UPU). A primeira letra indica a modalidade do objeto (exemplos com dígitos inventados):

| Código (inventado) | O que é |
| --- | --- |
| `LT482917367CN` | `L`: pequeno pacote rastreado (correspondência). |
| `RR482917367CN` | `R`: remessa registrada. |
| `CP482917367CN` | `C`: encomenda postal. |
| `EA482917367CN` | `E`: EMS, o serviço expresso da China Post. |
| `UA482917367CN` | `U`: pequeno pacote com mercadoria "sem rastreamento voltado ao cliente", nos termos da UPU; não espere leituras após chegar ao país de destino. |
| `ZS48291736701` | `ZS`, `ZC` ou `ZA` seguidos de 11 dígitos: também respondidos pelo rastreador da China Post. |
| `LP48291736405812` | Não é China Post: código logístico da Cainiao (AliExpress). |
| `YT4829173640581926` | Não é China Post, mas [YunExpress](carrier:yunexpress). |

O nono dígito é um dígito verificador: um algarismo digitado errado quase sempre torna o código inválido. A UPU permite apenas um número desse formato por objeto, motivo pelo qual o mesmo código funciona no site dos Correios ou do correio do seu país assim que a encomenda chega ao destino.

Para alguns envios, o rastreador da China Post pede os últimos quatro dígitos de um telefone de contato registrado na remessa.

## Significado dos status de rastreamento da China Post

A China Post publica suas movimentações em chinês. Os nomes de locais aparecem entre colchetes `【…】`: uma unidade de triagem chinesa ou, no exterior, o nome do país em chinês (como `【巴西】` para o Brasil, `【英国】` para o Reino Unido ou `【美国】` para os Estados Unidos). Na ordem habitual do trajeto:

| A China Post informa | O que significa para você |
| --- | --- |
| `已揽收` | Coletado: a China Post recebeu fisicamente o pacote. |
| `邮件到达【…】` | Chegou a uma agência ou centro de triagem da China Post. |
| `邮件离开【…】，正在发往下一站` | Saiu dessa unidade rumo à próxima etapa. |
| `【…】退回，备注:安检退回` | Um centro de triagem devolveu o pacote na inspeção de segurança para novo tratamento antes de seguir viagem; não significa devolução ao vendedor. O Peek exibe "Precisa de atenção". |
| `送交出口海关` | Apresentado à alfândega de exportação chinesa. |
| `出口海关/留存待验` | Retido pela alfândega de exportação para conferência. |
| `出口海关/放行` | Liberado pela alfândega de exportação. |
| `【…】已出口直封` | Fechado em despacho de exportação em uma unidade internacional da China. |
| `已交承运商运输` | Entregue a uma transportadora para o transporte. |
| `航空公司接收`, `航空公司启运` | Recebido pela companhia aérea e embarcado no voo. |
| `飞机进港` | O voo pousou. |
| `到达寄达地处理中心` | Chegou ao centro de tratamento no país de destino. |
| `送交境外进口海关` | Encaminhado à alfândega de importação no destino. |
| `境外进口海关留存待验` | Retido pela alfândega de destino para fiscalização. |
| `寄达地海关允许进口` | Liberado pela alfândega de destino. |
| `离开【…】处理中心` | Saiu do centro de tratamento no destino. |
| `已到达【…】投递局` | Chegou à unidade de distribuição da sua região. |
| `【…】安排投递`, `邮件正在派送中` | Saiu para entrega. |
| `已试投/投递失败` | Tentativa de entrega sem sucesso pelo correio local. |
| `【…】已妥投`, `已签收` | Entregue ao destinatário. |
| `已退回` | Devolvido ao remetente. |

As últimas movimentações vêm do serviço postal do país de destino repassadas pela China Post, às vezes somente após a entrega. O rastreamento do próprio correio local, com o mesmo código, costuma mostrar o trecho final em primeira mão.

## Quem entrega encomendas da China Post no Brasil e em outros países

A China Post realiza a coleta e o despacho aéreo a partir da China; no destino, o correio nacional assume o desembaraço aduaneiro e a entrega no endereço:

- **Brasil:** [Correios](carrier:correios-br). Acompanhe o mesmo código `…CN` pelo rastreamento dos Correios ou no portal Minhas Importações. Em compras internacionais, os tributos seguem o [Programa Remessa Conforme da Receita Federal](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona): lojas certificadas recolhem os impostos na compra (Imposto de Importação e ICMS estadual), enquanto remessas fora do programa são tributadas na entrada no Brasil.
- **Estados Unidos:** USPS.
- **Reino Unido:** Royal Mail (que conduz a encomenda pela alfândega britânica e avisa sobre VAT ou taxas). Envios EMS (iniciados por `E`) seguem pela Parcelforce.
- **Irlanda:** An Post. Na União Europeia, além de eventuais impostos, há cobrança da taxa europeia de manuseio de €2 por categoria de produto em encomendas vindas de fora do bloco; caso a loja não tenha cobrado no checkout, o correio solicita o pagamento antes da entrega.
- **Austrália:** Australia Post, que observa que o nível de rastreamento depende do serviço contratado na origem e que a alfândega pode levar até 15 dias.

Tentativas de entrega, avisos de retirada e prazo de guarda na agência seguem as regras do correio do seu país; veja também [entrega não realizada](guide:missed-delivery) e [encomenda não retirada](guide:uncollected-parcel). Sobre taxas e fiscalização, consulte [encomendas retidas na alfândega](guide:customs).

## Rastreamento China Post parado, perdido ou devolvido

**Sem movimentações ainda.** A China Post não exibe histórico antes da primeira leitura física; antes disso, a loja apenas gerou o código. Pergunte ao vendedor quando a postagem foi entregue. Enquanto isso, o Peek exibe "Aguardando China Post". Se após dois dias a China Post ainda não reconhecer o código e nenhum outro serviço responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda."

**Sem atualização após sair da China.** Entre o embarque aéreo e a primeira leitura do correio nacional podem passar vários dias sem novidades. Consulte o mesmo código no correio do seu país. Códigos iniciados por `U` podem não receber leituras no país de destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating) e [como rastrear encomendas da China](guide:tracking-from-china).

**Consta como entregue, mas não chegou.** Quem efetuou a entrega foi o correio do seu país: comece conferindo o rastreamento e o comprovante local e depois acione a loja. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extravio ou avaria.** A China Post determina que pedidos formais de investigação sobre remessas internacionais devem ser abertos pelo remetente na agência postal de origem (regra igualmente seguida por USPS e Australia Post). Portanto, fale com a loja ou marketplace para pedir reembolso ou reenvio; veja [encomenda perdida](guide:lost-parcel).

**Devolvida.** `已退回` indica que o pacote está retornando ao remetente. Fale com a loja para definir reenvio ou estorno; veja [devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da China Post

O site em inglês da China Post divulga as seguintes linhas:

- **Logística e EMS:** `8610 11183`.
- **Serviço Postal:** `8610 11185`.

A China Post não informa quais idiomas são atendidos nessas linhas. Como investigações formais exigem abertura pelo remetente, contatar a loja costuma ser mais rápido. Para entrega, retirada ou alfândega no Brasil, fale diretamente com os Correios.

## Rastrear encomendas da China Post com o Peek

O [Peek](/) identifica imediatamente códigos de duas letras, nove dígitos e `CN` como China Post quando o dígito verificador é válido — exceto prefixos `YY`, que pertencem à YYpost. Se o dígito verificador estiver incorreto, o Peek não reconhece como China Post e, ao tentar escolhê-la manualmente, informa que a transportadora "não emite códigos como este": confira o código no e-mail da loja. Códigos `ZS`, `ZC` ou `ZA` com 11 dígitos também são reconhecidos na hora. Para outros formatos, cole normalmente: o Peek acompanha mais de 4.500 transportadoras, e você pode ajustar tocando em "Trocar".

O Peek consulta diretamente o rastreamento da China Post. Quando a China Post solicita os quatro dígitos do telefone, o Peek tenta a visualização pública em `ems.com.cn` sem pedir seu telefone; se essa visualização também exigir os dígitos e nenhum serviço de rastreamento tiver a encomenda, o Peek avisa que faltam dados e você pode consultar no site da China Post com os quatro dígitos. Como a consulta pública da China Post mostra apenas as três leituras mais recentes, o Peek acrescenta a data de postagem quando disponível e busca histórico mais completo nos serviços de rastreamento quando há cobertura.

O Peek exibe a etapa, o histórico com a unidade de cada leitura (traduzindo o nome da cidade chinesa quando reconhecido) e o país de destino. Leituras cujo texto o Peek conhece aparecem em inglês; as demais, em chinês. A China Post registra suas leituras no horário da China, mesmo ao chegar ao exterior, enquanto leituras repassadas pelo correio local geralmente mantêm o fuso local; o Peek mostra cada leitura no horário do seu aparelho e, em peektracker.com e nos apps para iPhone e Android, encerra o histórico com "Horários no seu fuso horário:" e o nome do fuso. A China Post não informa data prevista, peso nem ponto de retirada, e o Peek omite nome do serviço, contatos, dados pessoais, nomes/telefones de carteiros e coordenadas.

Em códigos terminados em `CN`, assim que a China Post informa o país de destino, o Peek também consulta o correio nacional desse país com o mesmo código (os Correios, no Brasil) e passa a segui-lo quando ele registra leituras mais novas, preservando o histórico da China Post. Se a China Post não informar o país, o Peek usa o seu: nos apps para iPhone e Android, a região configurada no celular; em peektracker.com, o país de onde você adicionou a encomenda.

O Peek consulta a China Post de hora em hora, inclusive no dia da entrega; quando o correio local assume a entrega, o Peek passa a seguir o ritmo daquela transportadora. Você pode ativar alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a China Post, o correio local nem a loja em seu nome.

## Perguntas frequentes sobre o rastreamento China Post

### Onde está minha encomenda da China Post?

A última leitura indica onde ela se encontra. Uma unidade chinesa entre colchetes mostra que ainda está na China; a partir de `到达寄达地处理中心`, já chegou ao país de destino e pode ser acompanhada nos Correios ou no correio nacional com o mesmo código.

### Por que o rastreamento da China Post parou de atualizar?

Na maioria das vezes o pacote está em voo internacional ou aguardando fiscalização aduaneira entre a saída da China e a primeira leitura no destino. Verifique o mesmo código no site do correio do seu país. Códigos iniciados por `U` podem não receber leituras após chegarem ao Brasil.

### Quanto tempo demora uma entrega da China Post?

O prazo varia conforme a modalidade paga pela loja (pequeno pacote econômico, registrado ou EMS) e o tempo de liberação alfandegária no destino. Siga a estimativa informada pela loja ou marketplace no seu pedido.

### O que significa `已妥投` no rastreamento da China Post?

Significa entregue. Se você não recebeu o pacote, verifique primeiro no rastreamento dos Correios ou do correio do seu país e consulte [entregue, mas não recebido](guide:delivered-not-received).

### Quem entrega encomendas da China Post no Brasil e no exterior?

O serviço postal nacional do país de destino: os [Correios](carrier:correios-br) no Brasil, o USPS nos Estados Unidos e o Royal Mail no Reino Unido — todos sob o mesmo código `…CN` da UPU.

:::sources
- [China Post: Introduction to China Post Group](https://english.chinapost.com.cn/en/folder/1408/3969-1.htm) – serviço postal nacional estatal da China
- [China Post: EMS and Logistics](https://english.chinapost.com.cn/en/folder/1408/3923-1.htm) – marca expressa EMS
- [China Post: Contact us](https://english.chinapost.com.cn/en/category/1408/3925-1.htm) – sites de rastreamento e abertura formal de investigação pelo remetente
- [China Post: Home page (English)](https://english.chinapost.com.cn/) – telefones de Logística/EMS (`8610 11183`) e Serviço Postal (`8610 11185`)
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – formato S10, letras de serviço, faixa `U` sem rastreio ao cliente e unicidade do código
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – tributação de importações postais e expressas no Brasil
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – desembaraço pelo Royal Mail no Reino Unido
- [Revenue: Union Handling Fee press release](https://www.revenue.ie/en/corporate/press-office/press-releases/2026/pr-092926-handling-fee.aspx) – taxa de manuseio de €2 na UE por categoria de produto
- [Australia Post: International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – rastreamento internacional conforme o serviço na origem
- [Australia Post: Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – prazo alfandegário de até 15 dias na Austrália
- [USPS: International Inquiries – The Basics](https://faq.usps.com/s/article/International-Inquiries-The-Basics) – investigação internacional aberta pelo remetente
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – leituras da China Post, verificação por 4 dígitos, visualização pública e comportamento no Peek
:::
