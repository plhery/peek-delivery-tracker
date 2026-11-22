---
title: Seguimento DHL eCommerce: números GM, estados e contacto
description: Segue uma encomenda DHL eCommerce (antiga DHL Global Mail): formato GM, estados explicados, entrega via USPS ou correio local e contacto.
slug: seguimento-dhl-ecommerce
published: 2026-11-23
updated: 2026-11-23
---

A DHL eCommerce, anteriormente DHL Global Mail, é a divisão de encomendas da DHL para lojas online. Faz a triagem da encomenda nos seus centros de distribuição e entrega-a depois a uma transportadora no teu país: [USPS](carrier:usps) nos EUA e, fora dos EUA, o correio local ou outra transportadora. Acompanha-a com o número do e-mail de envio da loja no portal Webtrack da DHL eCommerce ou cola-o na caixa acima.

## Formato do número de seguimento DHL eCommerce

Os códigos DHL eCommerce têm pelo menos dez caracteres e costumam começar por `GM`. O Webtrack aceita até 10 números de cada vez, separados por vírgulas. Não tens número? Pergunta à loja.

| Formato (inventado) | O que é |
| --- | --- |
| `GM4829103756182947` | `GM` e 16 a 18 dígitos: o formato habitual da DHL eCommerce. |
| `GM4829103756` | `GM` e 8 a 15 dígitos: um número `GM` mais curto. |
| `9261482910375618294751` | 22 dígitos começados por `9261` ou `9361`: o código USPS de uma encomenda nos EUA entregue à USPS. |
| `7394028156342819` | 16 ou 17 dígitos: outro formato da DHL eCommerce. |
| `0031482910375618294751829364728` | `0031` e 27 dígitos: outro formato da DHL eCommerce. |

A linha «Delivery by» no Webtrack indica a transportadora da última milha, cujo número próprio pode aparecer mais tarde.

Formatos parecidos: 16 dígitos também podem pertencer à TNT, Evri, Canada Post ou Correos Express, e um `9261` ou `9361` à USPS. Um código de 14 dígitos pertence à [DHL eCommerce UK](carrier:dhl-ecommerce-uk), rede distinta com seguimento próprio; um código `JVGL` à [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl); e a [DHL Express](carrier:dhl-express) e a [DHL Paket](carrier:dhl) usam outros formatos. Mais em [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento DHL eCommerce explicados

O Webtrack apresenta um estado resumido numa linha (`En Route`, `Delivered`, `Exception`…) acima do histórico («Tracking History») em maiúsculas. Eis os registos mais comuns por ordem:

| A DHL eCommerce indica | O que significa para ti |
| --- | --- |
| `LABEL CREATED` | A loja criou a etiqueta. A DHL eCommerce ainda não tem a encomenda. |
| `EN ROUTE TO DHL ECOMMERCE DISTRIBUTION CENTER OR AWAITING PROCESSING` | A caminho da DHL ou ainda à espera na loja. |
| `PACKAGE RECEIVED AT DHL ECOMMERCE DISTRIBUTION CENTER` | A DHL eCommerce já recebeu a encomenda. |
| `PROCESSED` | Triada no centro de distribuição. |
| `DEPARTURE ORIGIN DHL ECOMMERCE FACILITY` | Saiu do primeiro centro DHL eCommerce. |
| `ARRIVAL DESTINATION DHL ECOMMERCE FACILITY` | Chegou ao centro DHL eCommerce próximo da tua zona. |
| `CUSTOMS CLEARANCE` | Na alfândega. |
| `TENDERED TO DELIVERY SERVICE PROVIDER, ALLOW 1-3 DAYS FOR UPDATES FOR PACKAGES WITHIN THE US` | Entregue à transportadora final. Nos EUA, as atualizações podem parar 1 a 3 dias. |
| `OUT FOR DELIVERY` | Hoje com o estafeta ou carteiro. |
| `DELIVERY ATTEMPT FAILED` | Ninguém pôde receber. Procura um aviso da transportadora local. |
| `DELIVERED` | Entregue. `[IN/AT MAILBOX]` indica colocação na caixa de correio. |
| `RETURNED TO SENDER` | A regressar à loja. |

A versão em inglês britânico do Webtrack usa algumas fórmulas diferentes, como `PROCESSED THROUGH SORT FACILITY`. Encomendas nos EUA mostram também registos da USPS após a entrega, como `ARRIVAL AT POST OFFICE`.

Dois registos geram dúvidas: `DELIVERED TO RETURNS AGENT OR COLLEGE/UNIVERSITY FOR FINAL DELIVERY` significa entrega a um intermediário (como serviços postais de um campus) que a entregará depois a ti; `Picked up at parcelshop` indica que o remetente a deixou num ponto de entrega, não que esteja lá à tua espera.

## Como entrega a DHL eCommerce: USPS, correios locais e ausências

Nos EUA, as FAQ do Webtrack indicam que a USPS entrega na ronda habitual do correio. Em envios internacionais assume o operador local («local delivery service provider»), que pode ser o correio local ou outra transportadora.

Avisos, balcões de levantamento, prazos de guarda e novas tentativas seguem assim as regras dessa transportadora final. A DHL pede que verifiques a caixa de correio e transmitas instruções de entrega à USPS ou à transportadora local.

Alteração de morada: as FAQ do Webtrack indicam que, depois de a encomenda sair da loja, a DHL eCommerce não pode alterar a morada — fala com a loja.

## Encomenda DHL eCommerce atrasada, perdida ou devolvida

**Ainda sem registos.** As FAQ do Webtrack indicam que os eventos surgem normalmente em 24 a 48 horas; se nada aparecer após 48 horas, contacta a loja. Quando o Webtrack não encontra nada, recomenda confirmar o número e contactar o vendedor.

**Sem atualizações há dias.** As FAQ apontam meteorologia, feriados, alfândega, greves e fatores externos. Nos EUA o próprio registo de transferência prevê 1 a 3 dias sem novidades. Vê [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Não chegou.** Se não chegar até ao fim da janela prevista, as FAQ do Webtrack mandam contactar a loja. Na UE, o portal Your Europe lembra que o vendedor responde por danos até à entrega e deve entregar em 30 dias salvo outro acordo; caso contrário lembra-o e dá um prazo extra antes de cancelar e pedir reembolso.

**Alfândega.** `CUSTOMS CLEARANCE` significa que está na alfândega. Vê [encomendas retidas na alfândega](guide:customs) para saber o que poderás pagar.

**Devolvida.** `RETURNED TO SENDER` significa regresso à loja: pede reembolso ou reenvio.

## Apoio ao cliente e telefone DHL eCommerce

Contacta primeiro a loja, como aconselham as FAQ do Webtrack. A página «Contact Customer Service» do Webtrack apresenta um cartão por país:

- **Estados Unidos e Canadá** (encomendas expedidas dos EUA): +1 317-554-5191, segunda a sexta-feira das 8:00 às 20:00 EST (o cartão do Canadá indica 8:00–21:00 ET).
- **Alemanha:** remete para o [apoio ao cliente DHL Alemanha](https://www.dhl.de/de/privatkunden/hilfe-kundenservice.html), segunda a domingo das 7:00 às 20:00 (hora alemã).
- **Irlanda:** 818 370050 (a partir da Irlanda), segunda a sexta-feira das 8:00 às 18:00 (hora irlandesa).
- **Austrália:** +61 37 0356818, segunda a sexta-feira das 8:00 às 18:00 (hora local).
- **Áustria e Suíça:** +49 228 92 95 95 95, segunda a sexta-feira das 9:00 às 18:00 (desde as 8:00 na Áustria, hora da Europa Central).
- **Reino Unido:** indica a linha da DHL eCommerce UK, 024 7693 7770. Vê a página [DHL eCommerce UK](carrier:dhl-ecommerce-uk).

O formulário «Contact Support» do Webtrack indica resposta em 24 a 72 horas. O custo das chamadas não é referido.

## Seguir uma encomenda DHL eCommerce com o Peek

O [Peek](/) reconhece de imediato a DHL eCommerce («Transportadora detetada») para `GM` e 16 a 18 dígitos. Num código `GM` mais curto (8 a 15 dígitos) consulta a DHL eCommerce e outras transportadoras compatíveis. Em formatos partilhados (16 ou 17 dígitos, `9261`/`9361`), pergunta em simultâneo às transportadoras prováveis (como TNT em 16 dígitos, ou Austrian Post e depois USPS em `9261`/`9361`). Se só a DHL eCommerce a conhecer, o Peek indica-o; se várias marcas a encontrarem, apresenta cada uma marcada com «Tem esta encomenda» para escolheres. Em «Alterar» (ou «Alterar transportadora») aceita qualquer código de 5 a 40 letras e dígitos para a DHL eCommerce. Também funcionam ligações `webtrack.dhlglobalmail.com`, `ecommerceportal.dhl.com` e `dhl.com` quando o código corresponde à DHL eCommerce.

O Webtrack é consultado primeiro. Quando não encontra a encomenda, o Peek abre a página global de seguimento em `dhl.com` via navegador, o que demora mais e pode falhar se a DHL bloquear visitas automáticas.

O Peek mostra estado, cada registo com cidade e país no mapa, dia previsto quando a DHL o fornece, remetente, nome do serviço e hora de entrega; do Webtrack também peso e transportadora final. Omite a tua morada e a referência do cliente. Os textos mantêm o inglês americano da DHL e cada entrega surge como «Entregue». As horas são lidas no relógio local de cada registo e convertidas para o teu fuso horário («Horas no teu fuso horário:»).

Quando o Webtrack indica USPS, a encomenda pode ser acompanhada na USPS com o seu número mostrando «Entrega: USPS» (ou «Anunciada a USPS, ainda não recebida» enquanto a USPS não tiver registos próprios). Encomendas lidas pela página global da DHL (Europa, Ásia) não passam para um correio local. O Peek verifica até de 10 em 10 minutos, e até de 5 em 5 minutos a partir das 6:00 no relógio do último registo no dia em que entra «Em distribuição» enquanto tiveres a encomenda aberta no Peek ou as notificações ligadas (o eventual recurso a navegador limita o ritmo). Após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Nas notificações podes escolher «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega». Ao fim de quatro dias sem registo em trânsito, fora da alfândega ou de um problema reportado, o Peek mostra «Sem atualizações desde …», a nota «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a DHL eCommerce.» e «Pede ajuda a DHL eCommerce». O Peek segue mais de 4500 transportadoras, mas não pode contactar a DHL eCommerce nem a loja por ti.

## Perguntas sobre o seguimento DHL eCommerce

### A DHL eCommerce é o mesmo que a DHL Express?

Não. A DHL eCommerce é a divisão de encomendas da DHL para lojas online e costuma deixar a entrega final ao correio local ou a outra transportadora. A DHL Express tem seguimento próprio e o Peek acompanha-as separadamente.

### A DHL eCommerce entrega através da USPS?

Nos Estados Unidos sim: segundo as FAQ do Webtrack, a USPS faz a entrega final. No estrangeiro entrega o correio local ou outra transportadora.

### Porque ainda não há dados de seguimento DHL eCommerce?

As FAQ do Webtrack dizem que os eventos costumam surgir em 24 a 48 horas. Se após 48 horas nada aparecer, contacta a loja que expediu a encomenda.

### A DHL eCommerce é legítima?

Sim. É a divisão de encomendas de comércio eletrónico da DHL, anteriormente chamada DHL Global Mail.

:::sources
- [DHL eCommerce: Webtrack tracking](https://webtrack.dhlecs.com/) – site de seguimento, estados, caixa de detalhes, até 10 números, conselhos se não encontrado, formulário de contacto, atualizações por e-mail
- [DHL eCommerce: Webtrack Contact Customer Service](https://webtrack.dhlecs.com/support/customer-service) – cartões de país, telefones, horários, FAQ de 24–48 horas, entrega USPS, atrasos, encomendas perdidas, alteração de morada, instruções
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilidade do vendedor até à entrega, 30 dias, prazo extra, reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – antiga DHL Global Mail, formatos de número e código USPS, textos e etapas, seguimento separado da DHL Express, o que o Peek lê e mostra
:::
