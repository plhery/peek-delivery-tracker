---
title: UPS tracking: número 1Z, estados e entregas em Portugal
description: UPS tracking em Portugal: como é o número 1Z, o que querem dizer Exception e as outras leituras, os Access Point, a alfândega e os teus direitos.
slug: ups-tracking
published: 2026-10-13
updated: 2026-10-27
---

Os números de seguimento da UPS (United Parcel Service) começam normalmente por `1Z`. Cola o número, ou um link de seguimento da UPS, na caixa acima para seguires a encomenda; a página de seguimento da própria UPS está no ups.com.

## Número de seguimento UPS: onde está e que formato tem

Quem te dá o número é a loja que envia a encomenda, normalmente no e-mail de envio ou na página da encomenda. Um link de seguimento do ups.com também o traz, a seguir a `tracknum=`. Não encontras nada? Vê [onde encontrar o número de seguimento da encomenda](guide:find-tracking-number).

| Número (inventado) | O que é |
| --- | --- |
| `1Z1234567812345674` | O número de encomenda da UPS: `1Z` seguido de 16 letras e algarismos, 18 caracteres ao todo. O último é um algarismo de controlo calculado a partir dos 15 anteriores. |
| `H1234567890` | Uma letra (H, J, K ou V) seguida de 10 algarismos. Pode ser um número da UPS, mas outras transportadoras usam o mesmo formato. |

## Estados do UPS tracking: o que quer dizer «Exception»

A página de seguimento da UPS em inglês tem uma barra de progresso (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) e, por baixo, as leituras. O Peek lê essas leituras em inglês e mostra por cima o estado em português. As principais, mais ou menos pela ordem em que a encomenda passa por elas:

| A UPS escreve | O Peek mostra | O que quer dizer para ti |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Etiqueta criada | O remetente criou a etiqueta. A UPS ainda não tem a encomenda. |
| `Drop-Off`, `Pickup Scan` | Aceite pela transportadora | A UPS já a tem: o remetente deixou-a num Access Point ou a UPS foi buscá-la. O primeiro `Arrived at Facility` também conta aqui. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | Em trânsito | Circula na rede da UPS. `Export Scan` e `Import Scan` marcam a passagem de uma fronteira. |
| `Your package is prepared for clearance.` | Na alfândega | Está a ser desalfandegada. |
| `Duties or taxes are due on this package.` | Na alfândega | Tens de pagar encargos de importação antes de a UPS a entregar (ver abaixo). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | Em distribuição | Vai com um estafeta. |
| `The receiver was not available at the time of the final delivery attempt.` | Tentativa de entrega | Também a última tentativa de entrega falhou. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Pronta para levantamento | Está à tua espera num UPS Access Point. |
| `DELIVERED` | Entregue | Entregue, ou levantada por ti num Access Point. |
| `Return to Sender` | Devolvida ao remetente | Vai voltar ao remetente. |

`Exception` é o estado genérico da UPS e pode indicar só um atraso. O que conta é a leitura por baixo: `We're sorry this package may experience a temporary delay.` é isso mesmo, enquanto `The company or receiver name is incorrect. This will delay delivery.` quer dizer que a entrega depende dos teus dados (o Peek mostra «Problema reportado»). O Peek guia-se pela leitura mais recente, não pelo estado genérico.

## Entrega UPS falhada e Access Point

Quando o estafeta não consegue entregar, a leitura diz que o destinatário não estava ou que o estabelecimento estava fechado, e a UPS tenta outra vez. Depois da última tentativa, uma nova leitura mostra o que acontece: a UPS guarda a encomenda, leva-a a um Access Point ou devolve-a ao remetente. Mais em [encomenda não entregue](guide:missed-delivery).

A UPS também pode levar a encomenda a um UPS Access Point. Aí as leituras dizem `Delivered to UPS Access Point™` e depois `UPS Access Point™ possession`, e o Peek mostra o nome e a morada do ponto. Não esperes demasiado: quando uma encomenda que ninguém levantou volta para trás, a leitura diz `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Se um Access Point fechar, a UPS recolhe as encomendas e a leitura indica que a entrega noutro ponto está pendente. Vê [quanto tempo tens para levantar uma encomenda](guide:uncollected-parcel).

## Alfândega: o que pagas numa encomenda UPS

Uma encomenda que chega de fora da UE pode ter encargos de importação a pagar antes de a UPS a entregar; o seguimento mostra então `Duties or taxes are due on this package.` e `Your package is being held until duties and taxes are paid. Awaiting customer action.`

Segundo a Autoridade Tributária e Aduaneira, desde 1 de julho de 2026 as compras online importadas numa remessa até 150 € pagam, além do IVA, 3 € de direitos aduaneiros por adição, ou seja, por tipo de produto: uma remessa de 100 € com uma camisa e um chapéu paga 6 €. Encomendas feitas e enviadas em separado contam como remessas distintas, mesmo que cheguem no mesmo dia. Segundo o portal Your Europe da UE, cobrar este direito cabe ao vendedor ou à transportadora, e pode já vir incluído no que pagas online. Mais em [encomenda retida na alfândega](guide:customs).

## Encomenda UPS parada, atrasada ou perdida

Se o seguimento parou, vê [porque é que o tracking não atualiza](guide:tracking-not-updating).

Se compraste numa loja, é ela que responde pela entrega, mesmo que seja a UPS a levar a encomenda. Segundo o Your Europe, o vendedor é responsável por qualquer dano desde a expedição até à entrega. Salvo acordo para outro prazo, tem 30 dias para entregar; se não entregar, avisa-o e dá-lhe um prazo suplementar razoável. Se também não o cumprir, podes rescindir o contrato e ser reembolsado o mais rapidamente possível.

Para reclamar, o Your Europe aconselha a tentar primeiro resolver o problema diretamente com a empresa, com documentos que sustentem o pedido, como recibos ou faturas. Se não chegares a acordo, podes recorrer a um organismo de resolução alternativa de litígios: em Portugal, um centro de arbitragem de conflitos de consumo.

**Diz entregue mas não está cá?** A página da UPS pode mostrar onde a encomenda ficou (`Left at: Front Door`, por exemplo) e uma prova de entrega, que o Peek não guarda. Vê no ups.com e depois fala com a loja. Mais em [encomenda dada como entregue mas não recebi](guide:delivered-not-received) e [encomenda extraviada ou perdida](guide:lost-parcel).

## Seguir uma encomenda UPS com o Peek

O [Peek](/) reconhece logo a UPS num número `1Z` cujo último algarismo passa no controlo da UPS. Se não passar, propõe só a UPS, que o recusa por estar mal escrito. O Peek só procura números `1Z` na UPS: outras formas, como `H1234567890`, ficam para serviços de seguimento, mesmo depois de «Alterar transportadora». Também podes colar um link do ups.com ou qualquer link com um número `1Z`.

O servidor do Peek abre a página de seguimento do ups.com num navegador e lê a resposta, de 10 em 10 minutos durante o dia, de 5 em 5 minutos quando a encomenda sai para entrega e de hora a hora ou de duas em duas horas à noite. Se a UPS recusar, ou se só der para ler o estado, serviços de seguimento preenchem o histórico. Vês o estado, cada leitura num mapa com o local e a hora exata, o dia de entrega previsto (nunca uma janela horária), a hora da entrega, o país de destino e, enquanto a encomenda espera, o nome e a morada do Access Point. As notificações escolhes tu: todas as atualizações, só as importantes ou só no dia da entrega.

Não vês quem assinou, a tua morada, a prova de entrega, onde ficou a encomenda nem a fotografia; peso e remetente, a UPS não os dá. As leituras aparecem no inglês da UPS e a entrega lê-se «DELIVERED». Nos Estados Unidos, quando a UPS passa uma encomenda Ground Saver ao correio americano (USPS) e indica o número, o Peek segue-a lá assim que o USPS mostra progresso e indica que é o USPS a entregar.

## Perguntas sobre o UPS tracking

### Quantos caracteres tem um número de seguimento UPS?

O mais habitual tem 18: `1Z` e mais 16 letras e algarismos, como `1Z1234567812345674`. O último algarismo serve de controlo, por isso um número mal copiado costuma ser recusado. Se a UPS não o reconhecer, confirma também se o e-mail da loja indica outra transportadora.

### Quanto tempo demora a aparecer o seguimento da UPS?

A primeira leitura, `Shipper created a label, UPS has not received the package yet.`, aparece assim que o remetente cria a etiqueta. Só avança quando o remetente entrega a encomenda ou a UPS a vai buscar (`Drop-Off`, `Pickup Scan`).

### Posso alterar a entrega UPS no Peek?

Não: o Peek só lê o seguimento da UPS. As alterações que fizeres com a UPS aparecem nas leituras, como `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Uma SMS a pedir para pagar uma encomenda UPS é verdadeira?

Desconfia. O Gabinete Cibercrime da Procuradoria-Geral da República alertou, em julho de 2023, para mensagens falsas em nome dos CTT que dizem haver uma encomenda pendente e pedem uma pequena taxa, de 2 ou 3 euros, antes da entrega; o link abre uma página falsa que pede os dados do cartão e, a seguir, o código recebido por SMS. Lembra também que os serviços de entrega têm sido terreno fértil para este esquema. Ignora e apaga estas mensagens, sem responder, e confirma a encomenda tu mesmo no ups.com ou no Peek. Se já deste os dados do cartão, contacta o banco e cancela-o. Mais em [SMS ou e-mail falso: como saber se é burla](guide:delivery-scam-texts).

:::sources
- [Autoridade Tributária e Aduaneira: Mercadorias eCommerce](https://info.portaldasfinancas.gov.pt/pt/destaques/Paginas/Mercadorias_eCommerce.aspx) – 3 € por adição desde 1 de julho de 2026, remessas até 150 €, exemplo de 6 €, encomendas separadas
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – 30 dias, prazo suplementar, rescisão e reembolso, responsabilidade do vendedor, quem cobra os 3 €
- [Your Europe: Resolução extrajudicial de litígios](https://europa.eu/youreurope/citizens/consumers/consumers-dispute-resolution/informal-alternative-dispute-resolution/index_pt.htm) – resolver primeiro com a empresa, com documentos; depois resolução alternativa de litígios
- [CNIACC: Rede de arbitragem de consumo](https://www.cniacc.pt/pt/rede-de-arbitragem-de-consumo) – os centros de arbitragem de conflitos de consumo
- [Ministério Público, Gabinete Cibercrime: alerta de 5 de julho de 2023](https://cibercrime.ministeriopublico.pt/sites/default/files/documentos/pdf/2023.07.05_alerta_phishing_ctt_visa_4.pdf) – mensagens falsas de encomenda pendente, taxa de 2 ou 3 euros, o que fazer
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – formatos do número, algarismo de controlo, leituras da UPS, o que o Peek lê da UPS
:::
