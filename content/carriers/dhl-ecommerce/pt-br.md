---
title: Rastreamento DHL eCommerce: códigos GM, status e contato
description: Rastreie encomendas DHL eCommerce (antiga DHL Global Mail): formato GM, significado dos status em inglês, entrega pela USPS ou correios locais e contatos.
slug: rastreamento-dhl-ecommerce
published: 2026-11-25
updated: 2026-11-25
---

A DHL eCommerce, antiga DHL Global Mail, é a divisão de encomendas da DHL voltada para lojas online. Ela faz a triagem do pacote nos próprios centros de distribuição e depois o repassa a uma transportadora do seu país: [USPS](carrier:usps) nos Estados Unidos e, em outros países, os correios locais ou outra transportadora. Rastreie com o código do e-mail de envio da loja no site Webtrack da DHL eCommerce ou cole-o na caixa acima.

## Formato do código de rastreamento DHL eCommerce

Os códigos da DHL eCommerce têm pelo menos dez caracteres e costumam começar com GM. O Webtrack aceita até 10 códigos por vez, separados por vírgula. Não recebeu nenhum código? Pergunte à loja.

| Formato (inventado) | O que é |
| --- | --- |
| `GM4829103756182947` | GM seguido de 16 a 18 dígitos: o formato GM habitual da DHL eCommerce. |
| `GM4829103756` | GM seguido de 8 a 15 dígitos: um código GM mais curto. |
| `9261482910573648291057` | 22 dígitos iniciados por 9261 ou 9361: o código da USPS usado nos EUA após o repasse à USPS. |
| `7394028156342819` | 16 ou 17 dígitos: outro formato da DHL eCommerce. |
| `0031482910573648291057364829105` | 0031 seguido de 27 dígitos: outro formato da DHL eCommerce. |

A linha "Delivery by" no Webtrack indica a transportadora de última milha, cujo próprio código pode aparecer depois.

Formatos parecidos: 16 dígitos também podem pertencer à TNT, Evri, Canada Post ou Correos Express, e um número iniciado por 9261 ou 9361 à USPS. Códigos de 14 dígitos podem ser da [DHL eCommerce UK](carrier:dhl-ecommerce-uk) (uma rede separada com rastreamento próprio), códigos `JVGL` pertencem à [DHL eCommerce Países Baixos](carrier:dhl-ecommerce-nl), e [DHL Express](carrier:dhl-express) e [DHL Paket](carrier:dhl) usam outros formatos. Veja mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da DHL eCommerce explicados

O Webtrack exibe um status resumido em uma linha (`En Route`, `Delivered`, `Exception`…) acima do histórico ("Tracking History") em letras maiúsculas. Estes são os registros mais vistos, na ordem em que a encomenda passa por eles:

| A DHL eCommerce mostra | O que significa para você |
| --- | --- |
| `LABEL CREATED` | A loja gerou a etiqueta. A DHL eCommerce ainda não recebeu o pacote. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | A caminho da DHL ou ainda aguardando retirada na loja. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | A DHL eCommerce já recebeu o pacote. |
| `PROCESSED` | Triado no centro de distribuição. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | Saiu da primeira unidade da DHL eCommerce. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Chegou à unidade da DHL eCommerce próxima a você. |
| `CUSTOMS CLEARANCE` | Está na alfândega. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Repassado à transportadora que fará a entrega. Nos EUA, as atualizações podem pausar por 1 a 3 dias. |
| `OUT FOR DELIVERY` | Com o motorista ou carteiro hoje. |
| `DELIVERY ATTEMPT FAILED` | Ninguém pôde receber. Procure um aviso deixado pela transportadora entregadora. |
| `DELIVERED` | Entregue. |
| `RETURNED TO SENDER` | Voltando para a loja. |

Encomendas nos EUA também exibem eventos da USPS após o repasse, como `ARRIVAL AT POST OFFICE`.

Um registro costuma gerar confusão: `Picked up at parcelshop` significa que o remetente entregou o pacote em um ponto parceiro, não que há algo aguardando retirada por você.

## Como a DHL eCommerce entrega: USPS, correios locais e ausências

Nos Estados Unidos, o FAQ do Webtrack informa que a USPS entrega a encomenda no horário habitual do correio. Em envios internacionais, o "prestador de entrega local" assume o trecho final, podendo ser os correios locais ou outra transportadora.

Por isso, avisos de tentativa, balcões de retirada, prazo de guarda e novas tentativas seguem as regras dessa transportadora final. A DHL orienta conferir a caixa de correio e passar instruções de entrega diretamente à USPS ou à transportadora local.

Alteração de endereço: o FAQ do Webtrack informa que, depois que o pacote sai da loja, a DHL eCommerce não consegue alterar o endereço; fale com a loja.

## Encomenda DHL eCommerce atrasada, extraviada ou devolvida

**Ainda sem eventos.** O FAQ do Webtrack diz que as movimentações costumam surgir em 24 a 48 horas; se nada aparecer após 48 horas, fale com a loja. Quando o Webtrack não encontra nada, recomenda conferir o código e contatar a loja.

**Dias sem atualização.** O FAQ cita clima, feriados, alfândega, greves e outros fatores fora do controle da DHL. Nos EUA, o próprio evento de repasse pede 1 a 3 dias para novas atualizações. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Não chegou.** Se o pacote não chegar até o fim da janela prevista de entrega, o FAQ orienta falar com a loja. Na UE, o portal Your Europe acrescenta que o vendedor responde por danos até a entrega e deve entregar em até 30 dias salvo acordo diverso; se não cumprir, você pode cobrar um prazo extra e depois cancelar o contrato com reembolso.

**Alfândega.** `CUSTOMS CLEARANCE` indica passagem pela alfândega. [Encomendas retidas na alfândega](guide:customs) explica o que pode ser cobrado.

**Devolvida.** `RETURNED TO SENDER` significa retorno à loja: peça reembolso ou novo envio à loja.

## Atendimento ao cliente e telefone da DHL eCommerce

Procure a loja primeiro, como recomenda o FAQ do Webtrack. A página "Contact Customer Service" do Webtrack traz um cartão por país:

- **Estados Unidos e Canadá** (pacotes enviados dos EUA): +1 317-554-5191, segunda a sexta das 8h às 20h EST (o cartão do Canadá informa 8h às 21h ET).
- **Alemanha:** encaminha para o [atendimento da DHL na Alemanha](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), segunda a domingo das 7h às 20h.
- **Irlanda:** 818 370050 (ligando da Irlanda), segunda a sexta das 8h às 18h.
- **Austrália:** +61 37 0356818, segunda a sexta das 8h às 18h.
- **Áustria e Suíça:** +49 228 92 95 95 95, segunda a sexta das 9h às 18h (o cartão da Áustria informa 8h).
- **Reino Unido:** lista a linha da DHL eCommerce UK, 024 7693 7770.

O formulário "Contact Support" do Webtrack informa retorno "em 24 a 72 horas". O custo das ligações não é informado.

## Rastrear uma encomenda DHL eCommerce com o Peek

O [Peek](/) identifica a DHL eCommerce na hora para GM seguido de 16 a 18 dígitos. Para códigos GM mais curtos, de 8 a 15 dígitos, consulta DHL eCommerce e Aramex simultaneamente (e também a Colis Privé, se você adicionar o código da França) e atribui à DHL eCommerce se ela reconhecer o código. Em formatos compartilhados, consulta as transportadoras prováveis em paralelo (TNT para 16 dígitos, além de Evri UK, Canada Post e Correos Express quando o último dígito valida; Colis Privé para 17 dígitos adicionados da França; Austrian Post para códigos iniciados por 9261 ou 9361 e, se nenhuma reconhecer, serviços de rastreamento, página da USPS e página global da DHL). De qualquer outro país, o Peek consulta a Colis Privé por último, depois que as outras transportadoras e os serviços de rastreamento não reconhecerem o código. Se apenas a DHL eCommerce reconhecer o código, você é avisado; se várias reconhecerem, o Peek lista cada uma com o selo "Reconhece este código" para você escolher. Em "Trocar", qualquer código com pelo menos 10 letras e dígitos pode ser associado à DHL eCommerce. Links de webtrack.dhlglobalmail.com e ecommerceportal.dhl.com também funcionam, assim como links de dhl.com com um formato DHL eCommerce diferente de um código GM curto.

O Webtrack é consultado primeiro. Se o pacote não aparecer lá, o Peek abre a página global em dhl.com, que leva mais tempo e pode falhar quando a DHL bloqueia acessos automatizados.

Você vê o status, cada movimentação com cidade e país no mapa, a data prevista quando informada pela DHL, o remetente e a hora da entrega; pelo Webtrack, também o peso e a transportadora final. O Peek omite seu endereço, a referência da loja e o nome de quem assinou. Os eventos mantêm o inglês norte-americano da DHL, e toda entrega concluída aparece como "Entregue".

Quando o Webtrack indica a USPS, o pacote passa a ser acompanhado pela USPS com o código dela assim que surgir um evento não mais antigo que o último da DHL eCommerce, exibindo "Entrega por USPS". Enquanto a USPS tiver apenas o anúncio do envio, sem movimentação própria, aparece "Anunciada a USPS, ainda não recebida". O Peek faz o mesmo com outras transportadoras finais indicadas pelo Webtrack, como La Poste, Austrian Post, Poczta Polska, Correos ou Poste Italiane. Encomendas lidas apenas pela página global de dhl.com não passam para os correios locais. Os alertas podem seguir "Cada movimentação", "Etapas importantes" ou "Só a entrega". Enquanto o pacote está aberto no Peek, ou no dia da entrega com alertas ligados, ele consulta até a cada 10 minutos e, no dia em que sai como "Saiu para entrega", a partir das 6h no relógio do último evento, a cada 2 minutos enquanto o Webtrack responde — ou a cada 5 minutos quando a última consulta abriu dhl.com em navegador. Quando apenas serviços de rastreamento respondem, consulta a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta a cada hora, e após sete dias sem novidades em "Etiqueta criada", a cada 6 horas. Depois de quatro dias sem eventos em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek mostra "Sem atualizações desde …", o aviso "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando DHL eCommerce." e o link "Pedir ajuda a DHL eCommerce".

## Perguntas frequentes sobre o rastreamento DHL eCommerce

### A DHL eCommerce é a mesma coisa que a DHL Express?

Não. A DHL eCommerce é a divisão de encomendas da DHL para lojas online e costuma deixar a entrega final a cargo dos correios locais ou de outra transportadora. A DHL Express tem rastreamento próprio, e o Peek acompanha as duas como transportadoras separadas.

### A DHL eCommerce é entregue pela USPS?

Dentro dos Estados Unidos, sim: o FAQ do Webtrack informa que a USPS faz a entrega final. Fora dos EUA, os correios locais ou outra transportadora realizam a entrega.

### Por que ainda não há informações de rastreamento da DHL eCommerce?

O FAQ do Webtrack diz que as movimentações costumam aparecer em 24 a 48 horas. Se não houver nada após 48 horas, fale com a loja que enviou a encomenda.

### A DHL eCommerce é confiável?

Sim. É a divisão de encomendas da DHL voltada ao comércio eletrônico, anteriormente chamada de DHL Global Mail.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – site de rastreamento, rótulos de status, caixa de detalhes, 10 códigos, orientação sem resultado, formulário de contato e atualizações por e-mail
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – cartões por país, telefones, horários, FAQ de 24 a 48 horas, entrega pela USPS, atrasos, extravios, alteração de endereço e instruções
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilidade do vendedor até a entrega, prazo de 30 dias, prazo adicional e reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – antiga DHL Global Mail, formatos de código e código USPS, textos e etapas, rastreamento próprio da DHL Express e o que o Peek lê e mostra
:::
