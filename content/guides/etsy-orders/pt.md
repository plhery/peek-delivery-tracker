---
title: Seguimento Etsy: onde está a tua encomenda e quem a entrega
description: Porque é que a tua encomenda Etsy ainda diz «Não enviado», que transportadora a entrega, como é o número de seguimento e quando abrir um caso.
slug: seguimento-encomenda-etsy
picture: Um pacote atado com cordel e selado, uma carrinha dos correios e o Pip a correr apressado.
published: 2026-10-17
updated: 2026-10-17
---

A Etsy não envia nada: cada loja faz ou embala as suas encomendas, envia-as pela transportadora que escolhe e junta um número de seguimento quando o serviço o tem. A tua página Compras diz-te se a encomenda já saiu da loja e quando deve chegar; o número de seguimento diz-te onde está agora. Atrasada ou desaparecida? Pergunta primeiro ao vendedor e depois à Etsy.

## Porque é que a minha encomenda Etsy ainda diz «Não enviado»?

Porque o vendedor ainda não a fez, embalou ou marcou como enviada. A Etsy calcula a data de entrega prevista assim: «Tempo de processamento + tempo de envio da transportadora = data de entrega prevista». O tempo de processamento é o tempo de que o vendedor precisa para criar o artigo e prepará-lo para envio. O exemplo da Etsy: 3-5 dias de processamento e 2-4 dias de envio dão uma entrega dentro de 5-9 dias. No recibo também vês a data de envio com que o vendedor se comprometeu.

Em **Compras e avaliações** (app: **Eu** → **Compras**), cada encomenda mostra um destes estados:

| Estado | O que quer dizer |
| --- | --- |
| `Não enviado` | O vendedor ainda não finalizou a encomenda ou não a atualizou na Etsy |
| `Enviado` | O vendedor finalizou a encomenda na Etsy |
| `Pré-trânsito` | Já há número de seguimento, mas a transportadora ainda não leu o pacote |
| `Em trânsito` | A transportadora já o tem e está a caminho |
| `Entregue` | Chegou, e já podes deixar uma avaliação |

`Enviado` nem sempre quer dizer que uma transportadora tem o pacote. O vendedor pode marcar uma data de envio até 3 dias depois, e a Etsy avisa-te nesse dia. Só com etiquetas Asendia, Evri, FedEx, USPS (exceto cartas e envelopes) ou UPS compradas na Etsy é que o e-mail espera pela primeira leitura da transportadora. Se a data prevista já passou e a encomenda continua `Não enviado`, a Etsy diz que o vendedor pode ter-se esquecido de a assinalar como finalizada, ou ainda não a enviou: pergunta em **Ajuda com a encomenda**.

Onde está o número na Etsy e no e-mail de envio: [encontrar o número de seguimento](guide:find-tracking-number).

## Que transportadora entrega uma encomenda Etsy?

A que o vendedor escolheu. A Etsy só vende etiquetas de envio a vendedores dos Estados Unidos, do Canadá e do Reino Unido; os outros compram o porte por conta própria, nos correios ou numa transportadora. Para envios para o estrangeiro, a Etsy indica as transportadoras habituais em cada país:

| Vendedor em | Etiquetas vendidas na Etsy | Transportadoras indicadas pela Etsy para o estrangeiro |
| --- | --- | --- |
| Estados Unidos | USPS, [FedEx](carrier:fedex), [UPS](carrier:ups) (só dentro dos EUA), Global Postal Shipping | [DHL Express](carrier:dhl-express), FedEx, UPS, USPS |
| Reino Unido | Royal Mail, Evri | DHL, Evri, Royal Mail, UPS |
| Canadá | Canada Post, Chit Chats | Canada Post, Chit Chats, DHL, FedEx, UPS |
| Portugal | Nenhuma | [CTT](carrier:ctt), DHL, FedEx, UPS |
| China, Hong Kong | Nenhuma | [Yanwen](carrier:yanwen), [YunExpress](carrier:yunexpress), [4PX](carrier:4px), e SF Express a partir da China |

Se o vendedor usou uma transportadora com ligação direta à Etsy, aparece um botão **Acompanhar pacote** junto à encomenda. Se não, o número só aparece em **Ver recibo**, e segues o pacote no site da transportadora.

Desde 1 de julho de 2026, a Etsy deixou temporariamente de vender etiquetas USPS e Canada Post para Portugal, devido, segundo a própria Etsy, a uma nova taxa de processamento da UE que afeta algumas transportadoras. Os vendedores dos Estados Unidos usam então o Global Postal Shipping ou compram a etiqueta noutro sítio. Os pacotes da China seguem um caminho próprio: vê [seguir um pacote da China](guide:tracking-from-china).

## Como é um número de seguimento Etsy?

É o número da transportadora, não um número da Etsy. Os correios usam o formato postal internacional: 13 caracteres que se mantêm de país para país.

:::anatomy RR 00000000 5 US
- RR | Duas letras para o tipo de envio e o serviço
- 00000000 | Número de série, oito algarismos
- 5 | Algarismo de controlo, calculado a partir do número de série
- US | País dos correios que o atribuíram: os Estados Unidos
:::

As formas que o Peek conhece para as transportadoras acima, com algarismos inventados:

| O número é assim | Normalmente |
| --- | --- |
| Duas letras, nove algarismos e depois `PT`, `US` ou `GB` | CTT, USPS ou Royal Mail |
| 22 algarismos a começar por `91` a `95`, como `9400111111111111111114` | USPS |
| `1Z` + 16 letras e algarismos | UPS |
| 12 ou 15 algarismos | FedEx |

Não sabes qual tens? Escreve-o aqui:

:::checker
:::

Todos os outros formatos, transportadora a transportadora: [formatos de números de seguimento](guide:tracking-number-formats).

## Porque é que o seguimento Etsy para ou muda?

Quase sempre por uma destas razões:

- **Passou uma fronteira.** A Etsy avisa que «alguns envios internacionais ou transfronteiriços não têm dados de acompanhamento associados assim que a encomenda sair do país de origem». O seguimento deve voltar quando o pacote chegar a Portugal.
- **Os CTT ficaram com ele.** Com um número postal, os correios do vendedor mandam o pacote para o estrangeiro e os CTT entregam-no. A União Postal Universal só admite «um único identificador S10» por envio, por isso o mesmo número costuma funcionar também no site dos CTT.
- **Duas etiquetas, uma só viagem.** Com o Global Postal Shipping da Etsy, um vendedor dos Estados Unidos envia o pacote com uma etiqueta USPS para a Asendia, que lhe põe a etiqueta internacional. A Etsy junta as duas partes num só histórico em Compras e avaliações, e os e-mails dela indicam «Tracked on Etsy».
- **O vendedor juntou ou corrigiu o número tarde.** O vendedor pode editar o número durante 3 dias depois de finalizar a encomenda; depois disso, manda-te o correto por mensagem. Também pode juntar um pacote mais tarde, e o número aparece ao lado do primeiro.
- **Várias encomendas, uma só caixa.** Se o vendedor juntar várias encomendas tuas num pacote, todas têm o mesmo número.

:::journey
- shop | Artesão | Fá-lo e embala-o: até lá, `Não enviado`
- label | Etiqueta | `Pré-trânsito`: um número, ainda sem leitura
- truck | Correios de origem | Primeiras leituras no país do vendedor
- plane | Voo | Aqui o seguimento costuma ficar calado
- customs | Alfândega | Controlo, às vezes com encargos
- handover | CTT | Entregam-no, quase sempre com o mesmo número
- home | Tu | `Entregue`
:::

Quanto tempo pode durar um silêncio: [porque é que o seguimento não atualiza](guide:tracking-not-updating).

### Porque é que não há número de seguimento nenhum?

Nem todos os serviços o têm. A Etsy di-lo claramente: «Nem todos os métodos de envio incluem informações de acompanhamento.» Para cartas e envelopes dos Estados Unidos, as etiquetas da Etsy juntam seguimento através da Pitney Bowes, mas um envelope demasiado rígido pode não o mostrar. A Etsy exige seguimento aos vendedores dos Estados Unidos, mas qualquer vendedor pode marcar uma encomenda como não tendo seguimento e indicar o motivo. Sem número? Escreve ao vendedor em **Ajuda com a encomenda**: ele sabe que serviço usou e quando o enviou.

## Vou pagar alfândega numa encomenda Etsy do estrangeiro?

A Etsy diz que, em geral, é quem compra que paga direitos, impostos e taxas de desalfandegamento, quase sempre quando o pacote chega. Em Portugal aplicam-se as regras da UE, e a Etsy cobra uma parte logo no pagamento:

| Encomenda | O que se aplica |
| --- | --- |
| De um país da UE | O IVA está incluído no preço, não pagas mais nada |
| De fora da UE, abaixo de 150 EUR | A Etsy cobra o IVA no pagamento. Somam-se direitos aduaneiros de 3 EUR por artigo, no pagamento ou na entrega |
| De fora da UE, acima de 150 EUR | O IVA e os direitos aduaneiros podem ser cobrados na entrega, a não ser que o vendedor envie com os direitos pagos |

Um pacote atrasado ou que nunca chega porque recusaste ou não pagaste os encargos devidos na entrega não está coberto pela proteção de compras da Etsy. Mais sobre encargos e prazos: [pacote retido na alfândega](guide:customs).

## O que fazer se a tua encomenda Etsy está atrasada ou não chega?

Segue esta ordem:

:::steps
- Vê as datas | A data de entrega prevista está na encomenda. A Etsy recalcula-a se o vendedor enviar mais cedo.
- Segue-a na transportadora | Com Acompanhar pacote, ou com o número de Ver recibo no site da transportadora.
- Pergunta ao vendedor | Compras e avaliações, depois Ajuda com a encomenda. O vendedor pode ver o estado, mudar a morada ou reembolsar-te.
- Abre um caso | 48 horas depois do teu pedido, quando a data prevista já passou. Se a encomenda nunca chegou: 7 dias depois da data prevista máxima.
- Atenção ao prazo | Tens até 30 dias depois da data de entrega prevista.
:::

Com a proteção de compras da Etsy, uma encomenda elegível é reembolsada por inteiro se o artigo não chegar, chegar danificado, não corresponder à descrição, ou chegar 7 ou mais dias depois da data de entrega prevista máxima indicada na compra. O teu pedido em **Ajuda com a encomenda** mostra a partir de quando podes escolher **Abrir um caso** e até quando. A Etsy só processa reembolsos até 180 dias depois da compra.

Há casos que não estão cobertos: atrasos por greves da transportadora, condições meteorológicas extremas, guerras ou agitação social, encargos recusados à porta e encomendas que já contestaste junto do teu banco. Compraste como convidado? Associa primeiro a encomenda a uma conta Etsy. Se pagaste com PayPal fora do Etsy Payments, abre antes uma disputa no PayPal até 180 dias depois da compra.

### Diz «Entregue», mas não o tens

A Etsy aconselha primeiro estas verificações: pergunta a quem vive ou trabalha contigo, espera um dia se não tiveres pressa, contacta o posto de correios ou o centro de expedição da tua zona, e pergunta aos vizinhos. Nada? Manda ao vendedor um pedido em **Ajuda com a encomenda**: normalmente é ele quem abre a reclamação junto da transportadora por ti. Depois, abre um caso se não aparecer. Mais em [entregue mas não recebido](guide:delivered-not-received) e [pacote perdido](guide:lost-parcel).

## Posso seguir uma encomenda Etsy fora da Etsy?

Sim, com o número da transportadora. Cola-o no [Peek](/): reconhece os números dos correios e transportadoras acima. Num envio postal para o estrangeiro, quando os correios de origem indicam o país de destino mas não nomeiam nenhum parceiro, o Peek pode propor uma consulta com o mesmo número nos CTT. Mantém o histórico dos correios de origem e passa a seguir os CTT assim que o seguimento deles mostrar avanços novos. Os passos da Etsy ficam na Etsy: o Peek não vê uma encomenda `Não enviado` nem pode abrir um caso por ti.

:::sources
- [Etsy: Qual é o estado da minha encomenda?](https://help.etsy.com/hc/pt/articles/115015521948-Qual-%C3%A9-o-estado-da-minha-encomenda) – estados, Acompanhar pacote, Ver recibo, seguimento no estrangeiro, sem seguimento, conselhos se não chegar
- [Etsy: O que é a data de entrega prevista?](https://help.etsy.com/hc/pt/articles/360020601674-O-que-%C3%A9-a-data-de-entrega-prevista) – processamento mais envio, exemplo, 7 dias se não chegar
- [Etsy: Como definir tempos de processamento, perfis de processamento e datas de envio](https://help.etsy.com/hc/pt/articles/115015588087-Como-definir-tempos-de-processamento-perfis-de-processamento-e-datas-de-envio) – tempo de processamento, data de envio no recibo
- [Etsy: Como adicionar acompanhamento e assinalar uma encomenda como concluída](https://help.etsy.com/hc/pt/articles/115015774228-Como-adicionar-acompanhamento-e-assinalar-uma-encomenda-como-conclu%C3%ADda) – data de envio até 3 dias depois, aviso na primeira leitura, seguimento obrigatório nos EUA, 3 dias para editar, pacotes adicionados, encomendas combinadas
- [Etsy: Como comprar etiquetas de envio na Etsy](https://help.etsy.com/hc/pt/articles/360001967188-Como-comprar-etiquetas-de-envio-na-Etsy) – transportadoras, vendedores do Canadá, Reino Unido e EUA, destinos da UE suspensos
- [Etsy: FedEx Shipping Labels on Etsy (em inglês)](https://help.etsy.com/hc/en-us/articles/360000337447-FedEx-Shipping-Labels-on-Etsy) – vendedores dos EUA
- [Etsy: UPS Shipping Labels on Etsy (em inglês)](https://help.etsy.com/hc/en-us/articles/18309523715735-UPS-Shipping-Labels-on-Etsy) – vendedores dos EUA, só envios nacionais
- [Etsy: Royal Mail Postage Labels on Etsy (em inglês)](https://help.etsy.com/hc/en-gb/articles/30907634892311-Royal-Mail-Postage-Labels-on-Etsy) – vendedores do Reino Unido
- [Etsy: Evri Postage Labels on Etsy (em inglês)](https://help.etsy.com/hc/en-gb/articles/360002035507-Evri-Postage-Labels-on-Etsy) – vendedores do Reino Unido
- [Etsy: Chit Chats Shipping Labels on Etsy (em inglês)](https://help.etsy.com/hc/en-us/articles/44210045773719-Chit-Chats-Shipping-Labels-on-Etsy) – vendedores do Canadá
- [Etsy: Global Postal Shipping Labels on Etsy (em inglês)](https://help.etsy.com/hc/en-us/articles/360037382894-Global-Postal-Shipping-Labels-on-Etsy) – Asendia, duas etiquetas, Tracked on Etsy
- [Etsy: How to Ship First-Class Flats and Letters (em inglês)](https://help.etsy.com/hc/en-us/articles/360039466434-How-to-Ship-First-Class-Flats-and-Letters-with-Etsy-Shipping-Labels) – seguimento através da Pitney Bowes, envelopes rígidos
- [Etsy: Informações para a gestão de envios internacionais](https://help.etsy.com/hc/pt/articles/360001987487-Informa%C3%A7%C3%B5es-para-a-gest%C3%A3o-de-envios-internacionais) – transportadoras habituais por país
- [Etsy: Terei de pagar impostos, taxas alfandegárias ou tarifas sobre a minha encomenda?](https://help.etsy.com/hc/pt/articles/115015691007-Terei-de-pagar-impostos-taxas-alfandeg%C3%A1rias-ou-tarifas-sobre-a-minha-encomenda) – regras da UE, 150 EUR, 3 EUR por artigo
- [Etsy: Programa de proteção de compras da Etsy](https://help.etsy.com/hc/pt/articles/7471925990807-Programa-de-prote%C3%A7%C3%A3o-de-compras-da-Etsy) – o que está coberto, encargos aduaneiros, greves e meteorologia, convidados
- [Etsy: Como obter ajuda com uma encomenda](https://help.etsy.com/hc/pt/articles/4402660818583-Como-obter-ajuda-com-uma-encomenda) – Ajuda com a encomenda, o que o vendedor pode fazer
- [Etsy: Como abrir um caso](https://help.etsy.com/hc/pt/articles/5745586898199-Como-abrir-um-caso) – 48 horas, 30 dias, 180 dias, PayPal, estornos
- [Etsy: What if My Buyer's Package Gets Lost? (em inglês)](https://help.etsy.com/hc/en-us/articles/360050349953-What-if-My-Buyer-s-Package-Gets-Lost) – reclamação aberta pelo vendedor
- [UPU: norma S10 (em inglês)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – o formato de 13 caracteres, um número por envio
- [Universal Parcel Scraper: dados das transportadoras (em inglês)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formas dos números, o que o Peek verifica
:::
