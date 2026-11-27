---
title: Rastreio CACESA: alfândega, status e quem entrega
description: Rastreie encomendas via CACESA para Espanha ou Portugal: qual código usar, o que significam os status, pagamento de impostos, entrega final e contato.
slug: cacesa-tracking
published: 2026-11-27
updated: 2026-11-27
---

A CACESA (Compañía Auxiliar al Cargo Express) é uma despachante aduaneira em Madri para encomendas de compras on-line enviadas da China para a Espanha e Portugal: ela recebe os pacotes em vários aeroportos, faz o desembaraço alfandegário e os repassa à empresa que faz a entrega final. A CACESA não realiza entregas. Rastreie sua encomenda na página de rastreamento da CACESA usando o número de remessa (*shipment number*) fornecido pela loja, ou cole esse código na caixa acima.

## Qual código de rastreio usar na CACESA

A CACESA não publica um formato de código. Seu rastreador pede o "Shipment number", isto é, o código que a loja ou a operadora logística chinesa enviou junto com a encomenda. Ele aparece no e-mail de envio da loja ou na página do pedido. A CACESA não tem contato direto com o site onde você fez a compra.

Formatos observados no rastreador da CACESA (dígitos inventados):

| Formato | O que é |
| --- | --- |
| `CNES48291736548` | `CNES` seguido de 11 dígitos. |
| `CNESP48291736548291736548` | `CNESP` seguido de 20 dígitos, encomenda destinada à Espanha. |
| `CNPRT48291736548291736548` | `CNPRT` seguido de 20 dígitos, encomenda destinada a Portugal. |

O catálogo do Peek não vincula nenhum desses formatos diretamente à CACESA. Códigos `CNESP` e `CNPRT` também pertencem à [Ecoscooting](carrier:ecoscooting), o serviço de entrega da Cainiao na Espanha e em Portugal, e um código `LP` com 14 dígitos é a referência logística do AliExpress, rastreada pela [Cainiao](carrier:cainiao). Se o rastreador da CACESA não reconhecer seu código, tente a transportadora citada no e-mail da loja. Veja mais em [formatos de código de rastreio](guide:tracking-number-formats).

Você pode consultar vários códigos de uma vez no rastreador da CACESA. Não apareceu? A CACESA orienta conferir o número e lembra que pedidos recentes podem ainda não ter entrado no sistema. Encomendas antigas também deixam de aparecer no rastreador.

Uma carta da Agência Tributária espanhola (AEAT) sobre encomenda retida traz uma referência "HB". Esse código serve para atendimento na CACESA, não para rastreamento.

## Significado dos status de rastreio CACESA

O rastreador da CACESA mostra textos em espanhol na versão em espanhol e estes textos em inglês na versão em inglês. Cada linha tem data e hora, agrupadas sob Data registered, Warehouse ou Custom Clearance.

| Status da CACESA | O que significa para você |
| --- | --- |
| `Electronic notification of shipment received` | A CACESA recebeu os dados eletrônicos da encomenda. O pacote físico ainda não chegou. |
| `Arrival Notification` | A chegada da encomenda à CACESA foi anunciada. |
| `Inbound warehouse` | O pacote está no armazém da CACESA. |
| `Start Custom clearance Process` | O desembaraço alfandegário começou. |
| `Customs cleared-Green channel` | A alfândega liberou a encomenda: ela pode seguir para entrega. |
| `Parcel is going out from Cacesa facilities - More delivery information check with transport company.` | A CACESA entregou o pacote à transportadora responsável pela entrega final. |
| `Shipment not found` | A CACESA não reconhece esse código ou não o exibe mais. |

O canal verde (*Green channel*) é o caminho sem pendências. As perguntas frequentes da CACESA citam outros dois: laranja (a alfândega confere os documentos) e vermelho (a alfândega confere a mercadoria e os documentos). Se a alfândega precisar de algo seu, a CACESA entra em contato por e-mail ou SMS (veja abaixo).

A linha "going out" costuma ser a última exibida pela CACESA. A partir daí, a encomenda está com a transportadora local, e a página da CACESA orienta buscar detalhes de entrega com essa empresa.

## Entrega final, entregas não realizadas e devoluções na CACESA

A CACESA explica de forma direta: não é ela quem faz a entrega, mas sim a empresa de transporte escolhida pela loja virtual. Como a CACESA não informa o nome dessas transportadoras, procure essa informação no e-mail da loja ou na página do pedido.

É essa transportadora quem define dia de entrega, pontos de retirada, armários e entregas aos sábados. A CACESA não fornece dados sobre isso; pergunte à empresa de entrega. Após o número previsto de tentativas, a CACESA informa que a transportadora devolve a encomenda ao vendedor, mas não informa quantas tentativas são feitas.

Se a encomenda ficou retida, a CACESA leva de 3 a 4 dias após a liberação para repassá-la, somados ao prazo de entrega até o seu código postal.

A CACESA não cuida de devoluções feitas por compradores pessoas físicas. Combine a devolução com a loja ou com a transportadora.

## Alfândega CACESA: pagamento, documentos e problemas

**E-mail ou SMS da CACESA.** Uma mensagem dizendo que sua encomenda aguarda desembaraço alfandegário significa que a alfândega precisa de informações adicionais ou que há tributos a pagar antes da liberação. A CACESA pede o envio para atc@cacesa.com de:

- fatura ou comprovante de compra — por exemplo, captura de tela do pedido mostrando o número da encomenda, o item e o valor, junto com uma foto do produto;
- comprovante de pagamento, como a captura de tela da cobrança no cartão;
- uma nota informando se você é trabalhador autônomo, caso se aplique.

**Como pagar.** Em cacesa.com, acesse "Tax payment", informe o número da remessa e o valor da compra em euros para ver os tributos, impostos e o total. É possível pagar por cartão, PayPal ou Bizum. A CACESA recolhe o valor junto à Agência Tributária em seu nome, por isso o pagamento não pode ser estornado; valores pagos a maior são reembolsados pelo atendimento. Encomendas não pagas retornam à origem.

**Não quer mais o pacote?** Envie o formulário de abandono de mercadoria da CACESA para atc@cacesa.com para que ela providencie junto à Agência Tributária o retorno à origem.

**Carta da Agência Tributária.** Em casos de "Decomiso - Depósito temporal" ou "Validación de datos", digitalize a carta e envie para atc@cacesa.com. A CACESA informará se o pacote ainda pode ser recuperado.

O valor devido segue as regras detalhadas em [encomenda retida na alfândega](guide:customs). As perguntas frequentes da CACESA registram uma cobrança alfandegária de € 3 por tipo de produto em pedidos de até € 150 desde 1º de julho de 2026, paga uma única vez por itens que compartilhem o mesmo código HS de 6 dígitos; se o marketplace já cobrou IVA e tarifas no pagamento, nada mais é cobrado na entrega.

**Atraso, perda ou dano.** Para qualquer assunto além do pagamento de tributos, a CACESA direciona você ao vendedor, que firmou as condições de entrega. A encomenda foi liberada mas nunca chegou? Se ela não foi retida e a CACESA nunca entrou em contato, fale com o vendedor. Veja orientações em [rastreio sem atualização](guide:tracking-not-updating).

> A CACESA alerta que nem ela nem qualquer pessoa autorizada a entregar sua encomenda pedirá que você pague novamente IVA, direitos aduaneiros ou outras taxas já quitadas na compra. Não pague; confirme antes com o vendedor ou com a CACESA.

## Telefone e e-mail de contato da CACESA

Conforme listado em cacesa.com. A CACESA não tem site em português nem escritório em Portugal; os mesmos canais atendem ambos os países.

- **E-mail:** atc@cacesa.com, para encomendas retidas, envio de documentos, pagamentos e abandono.
- **Telefone:** +34 91 746 1860 (matriz, Calle Golf 23, Madri) ou +34 91 333 54 50 (escritório de carga aérea em Madri-Barajas). A CACESA não informa horário de atendimento nem linha exclusiva para destinatários.
- **Reclamações** sobre o próprio serviço da CACESA: [formulário de reclamações](https://www.cacesa.com/en/legal-complaints), informando a referência do envio e o motivo.
- **Onde está o pacote e quando chega:** consulte o vendedor ou a transportadora.

## Como acompanhar encomendas CACESA no Peek

Como nenhum formato de código é exclusivo da CACESA, o [Peek](/) nunca identifica a CACESA pelo número. Adicione o código e selecione CACESA em "Trocar", ou cole um link de rastreio de cacesa.com (`/en/tracking` ou `/es/seguimiento` com `busqueda=`), que identifica a CACESA para qualquer código. Já códigos `CNESP` ou `CNPRT` identificam a Ecoscooting na hora.

O Peek exibe os textos originais em inglês da CACESA, lendo os horários no fuso de Madri e convertendo para o fuso do seu aparelho. Uma linha de entrega aparece como "Entregue". `Start Custom clearance Process` aparece como "Na alfândega" e a liberação pelo canal verde volta a avançar, mas o Peek não exibe o valor dos impostos nem faz pagamentos. Como a CACESA não informa locais, nada é marcado no mapa, e não há previsão de entrega, peso, remetente ou destinatário.

A CACESA não informa o nome da transportadora final, e o pacote segue com o mesmo código. O Peek só consegue continuar o rastreamento quando esse código pertence claramente a outra transportadora: Ecoscooting para `CNESP` e `CNPRT`; para códigos postais internacionais, os correios do país onde o Peek situa você (pela região do celular ou, na web, pela origem da conexão), como [Correos](carrier:correos-spain) na Espanha ou [CTT](carrier:ctt) em Portugal; ou [CTT Express](carrier:ctt-express) para os códigos dela. Códigos `CNES` e `LP` não são seguidos depois. Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos (caso contrário, de hora em hora) e pode avisar quando o status mudar. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), passa a verificar de hora em hora.

## Perguntas sobre o rastreio CACESA

### O que é CACESA Postal?

CACESA — também chamada de Cacesa Postal ou CACESA Logistics — é a despachante aduaneira de Madri que realiza o desembaraço de compras on-line vindas da China para a Espanha e Portugal. Foi fundada pela Iberia em 1987 e adquirida pelos CTT de Portugal em 2025.

### Quem faz a entrega de uma encomenda CACESA?

A transportadora escolhida pelo vendedor, não a CACESA. O nome dela aparece no e-mail da loja ou na página do pedido; a CACESA não informa essa empresa.

### A CACESA faz o desembaraço aduaneiro do AliExpress?

As páginas da CACESA não citam lojas específicas. A empresa explica que recebe encomendas de consolidadoras na China e faz o desembaraço para os compradores, sem contato direto com o site onde a compra foi feita.

### Quanto tempo leva uma encomenda CACESA depois da alfândega?

Para pacotes retidos, a CACESA faz o repasse entre 3 e 4 dias após a liberação, mais os dias de entrega até o seu código postal. Para encomendas que não ficaram retidas, a CACESA não informa prazo.

:::sources
- [CACESA: Shipment tracking](https://www.cacesa.com/en/tracking) – rastreador, número de remessa, empresa de entrega, Tax payment
- [CACESA: Seguimiento de envíos](https://www.cacesa.com/es/seguimiento) – o mesmo em espanhol
- [CACESA: Faqs](https://www.cacesa.com/en/faqs) – atuação, sem entrega, canais, documentos, 3 a 4 dias, tentativas, devoluções, cartas da AEAT, abandono, atc@cacesa.com
- [CACESA: Offices](https://www.cacesa.com/en/offices) – telefones, ausência de escritório em Portugal
- [CACESA: Legal Warning](https://www.cacesa.com/en/legal-warning) – pagamentos, sem estorno, devolução se não pago, demais reclamações ao vendedor
- [CACESA: Complaints](https://www.cacesa.com/en/legal-complaints) – formulário de reclamações
- [CACESA: Quiénes somos](https://www.cacesa.com/es/quienes-somos) – razão social, 1987, Iberia
- [CACESA: site texts](https://www.cacesa.com/main.40b62b43302d800a.js) – rótulos do rastreador, texto de não encontrado, etapas de pagamento, FAQ de regras da UE, alerta de fraude, China para Espanha e Portugal
- [ECO: CTT results to September 2025](https://eco.sapo.pt/2025/10/30/lucro-dos-ctt-sobe-184-para-328-milhoes-ate-setembro-com-integracao-da-espanhola-cacesa/) – conclusão da compra pelos CTT
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
