---
title: Rastreio DAI Post: códigos, status e entrega na Oceania
description: Rastreie encomendas DAI Post (Parcel Right) na Austrália, Nova Zelândia ou Singapura: códigos, status, autorização de entrega, retirada e contato.
slug: dai-post-tracking
published: 2026-11-27
updated: 2026-11-27
---

A DAI Post é uma empresa australiana de entregas para lojas virtuais, fundada em Melbourne em 2000. Ela transporta encomendas dos Estados Unidos, Reino Unido, Europa e Ásia até compradores na Austrália, Nova Zelândia e Singapura. Sua própria entrega de última milha chama-se Parcel Right. Rastreie sua encomenda na [página de rastreio da DAI Post](https://daipost.com/track) ou cole o código na caixa acima. O número consta na confirmação de pedido da loja; se não encontrar, pergunte à loja.

## Formato do código de rastreio DAI Post

A DAI Post acompanha os códigos que as lojas imprimem nas encomendas, por isso existem vários formatos. Seu rastreador aceita até 10 códigos por vez. Veja os formatos conhecidos pelo código do Peek, com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `NZC482910375` | NZC seguido de nove dígitos: no código do Peek, número da DAI Post para encomendas destinadas à Nova Zelândia. O Peek identifica DAI Post na hora. |
| `00393580944829103754` | 20 dígitos começando por `0039358094`, com o último sendo dígito verificador: um SSCC (código de barras da etiqueta), que pode aparecer impresso como `(00) 3 9358094 482910375 4`. Outras transportadoras também usam 20 dígitos, então o Peek não tem certeza só pelo formato. |
| `KPR48291037561` | Referência própria da loja, com letras seguidas de dígitos. O Peek não reconhece pelo formato. |

Espaços, parênteses e letras minúsculas não importam. Se um único dígito de um código de 20 dígitos estiver errado, o dígito verificador falha e o Peek não o associa à DAI Post: confira o número. Veja mais em [como encontrar um código de rastreio](guide:find-tracking-number) e [formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio DAI Post

O rastreador da DAI Post exibe frases informais para cada etapa. Algumas encomendas mostram uma versão mais curta da mesma etapa, como `Checked in at origin hub`. Como o Peek lê o código interno da etapa, ambas significam o mesmo. Veja as mensagens conhecidas, em ordem:

| A DAI Post exibe | O que significa | O que o Peek mostra |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` ou `Customer label information received` | A loja enviou seus dados. A DAI Post ainda não está com o pacote. | "Etiqueta criada" |
| `Your parcel’s checked in and being prepared for its journey.` ou `Checked in at origin hub` | O pacote deu entrada no centro de origem da DAI Post. | "Recebida pela transportadora" |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` ou `Parcel processed at origin facility` | Triado e pronto para partir. | "Em trânsito" |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` ou `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | Atraso na partida do voo ou durante o trajeto. | "Em trânsito" |
| `Your parcel is in the air and is heading your way.` ou `Parcel departed origin country` | Em voo, saindo do país de origem. | "Em trânsito" |
| `Parcel arrived in trans-shipment country.`, depois `Your parcel’s left the transfer country and is back on the move.` | Escala em um país de trânsito no caminho. | "Em trânsito" |
| `Touchdown! Your parcel has landed.` ou `Parcel arrived in destination country` | Chegou ao seu país. | "Em trânsito" |
| `We’ve collected your parcel from the airline and it’s being processed.` ou `Collected from the airline for processing` | A DAI Post retirou da companhia aérea para processamento. | "Em trânsito" |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` ou `Parcel processed by clearance facility` | Em inspeção pela alfândega. | "Na alfândega" |
| `Customs clearance delay` | Ainda retida na alfândega. | "Na alfândega" |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` ou `Customs cleared` | Liberada pela alfândega para a etapa seguinte. | "Em trânsito" |
| `We’ve got your parcel and it’s been sorted for delivery.` ou `Parcel received and sorted` | Recebida e triada para entrega. | "Em trânsito" |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | Viajando entre estados. | "Em trânsito" |
| `Your parcel’s arrived at our local facility, it's on the way.` | Chegou a um depósito próximo de você. | "Em trânsito" |
| `Your parcel’s on its way to you, almost there!` | Indo para a unidade de entrega, ainda sem sair com o motorista. | "Em trânsito" |
| `It’s happening, your parcel will be delivered today. Get ready!` ou `Parcel out for final mile carrier delivery` | Saiu para entrega hoje. | "Saiu para entrega" |
| `Parcel unsafe to leave at address` | O motorista não deixou o pacote no local (veja abaixo). | "Tentativa de entrega" |
| `Parcel available for collection` | Aguardando em um ponto de retirada. | "Pronta para retirada" |
| `Delivered! Your parcel has arrived. Enjoy!` | Entregue. | "Entregue" |
| `Delivered, left where you can find it.` ou `Parcel delivered - authority to leave` | Deixada no endereço sem assinatura. | "Entregue" |

Para códigos desconhecidos, o rastreador da DAI Post responde `Sorry, we couldn't find your parcel.` Saiba mais em [significado dos status de rastreio](guide:tracking-statuses).

**Horários.** A DAI Post informa cada horário no relógio local do ponto de leitura, sem fuso horário. O Peek estima o fuso pelo local e converte para o fuso do seu dispositivo; a hora do primeiro registro pode apresentar pequena diferença.

## Entregas DAI Post: autorização para deixar, retirada e devoluções

A DAI Post afirma que sua rede cobre 100% da população australiana. O horário da entrega varia conforme a rota do motorista, o volume do dia e o trânsito.

- **Autorização para deixar (*Authority to leave*).** Não exige assinatura. Se ninguém atender e o motorista considerar o local seguro, ele deixa a encomenda.
- **Local inseguro.** Se o pacote ficar muito exposto, segue para o ponto de retirada mais próximo na sua região, e a DAI Post envia instruções para buscar. No site da DAI Post, a linha `Parcel available for collection` tem link para o mapa do local; o Peek mostra apenas a cidade.
- **7 dias para retirar.** Encomendas não retiradas em 7 dias voltam ao depósito da DAI Post e depois à loja — o mesmo vale para endereços incompletos.
- **Notificações.** Você recebe avisos das etapas principais apenas se a loja informou seu e-mail e celular à DAI Post.

Os termos da DAI Post acrescentam que, caso você não esteja presente, o pacote pode ser deixado em local seguro ou encaminhado a um ponto de retirada (*Collection Point*), passando a constar como entregue. O site não informa quem faz a entrega final na Nova Zelândia ou em Singapura. Veja [entrega perdida](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Alfândega em encomendas DAI Post

**Retida na alfândega (*Customs held*).** A página de ajuda da DAI Post explica que esse evento significa que a ABF ou a DAFF (agências australianas de fronteira e biossegurança) provavelmente precisam de informações extras antes da liberação. Consulte o atendimento da DAI Post: o formulário traz a opção "My parcel is Customs held."

**Taxas e tributos.** Os termos da DAI Post responsabilizam o remetente por todos os encargos do envio, incluindo impostos e direitos aduaneiros. Se você recusar pagar uma cobrança na entrega, o remetente continua responsável e a DAI Post pode reter ou devolver o pacote ao remetente. O que pode ser cobrado depende das regras do seu país: veja [encomendas retidas na alfândega](guide:customs). Recebeu mensagem suspeita cobrando taxa? Leia [golpes por SMS de entrega](guide:delivery-scam-texts).

## Rastreio DAI Post sem atualização, pacote perdido ou devolvido

**Dias sem nova movimentação.** A DAI Post registra leituras apenas em pontos-chave e lembra que o pacote continua em deslocamento entre eles. Em rotas interestaduais ou regionais, pode parecer parado por dias. Se ficar quatro dias sem nova linha em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek mostra o aviso com a data do último evento (por exemplo, "Sem atualização de rastreamento desde seg 12 out") e um link para ajuda da DAI Post. Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).

**Ainda em "Etiqueta criada".** A DAI Post ainda não recebeu o pacote. Se continuar assim por dias, pergunte à loja se realmente houve postagem.

**Entregue, mas não encontrou.** Como há autorização para deixar sem assinatura, procure ao redor do imóvel primeiro. A DAI Post pode enviar por e-mail o comprovante de entrega sem assinatura: peça pelo formulário de contato. Depois, fale com a loja. Veja [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Para pacotes que nunca chegaram, use o formulário da DAI Post selecionando "I did not receive my parcel." Em ambos os casos, foi a loja quem contratou o frete, então peça reembolso ou reenvio à loja. Veja [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato DAI Post

Canais oficiais da DAI Post:

- **Telefone:** Austrália +61 3 8586 1200, Nova Zelândia +64 800 451 718, Singapura +65 800 321 1587. Não há horário de atendimento publicado.
- **E-mail:** consumercare@daipost.com.
- **On-line:** [formulário de contato](https://daipost.com/contact), que solicita nome, telefone, e-mail, código de rastreio e motivo do contato.

## Acompanhe sua encomenda DAI Post no Peek

O [Peek](/) acompanha encomendas da DAI Post usando apenas o código, sem conta nem código postal.

- **Código `NZC`.** O Peek identifica DAI Post na hora ("Transportadora detectada"). Se após dois dias da primeira consulta a DAI Post ainda não reconhecer o código, o Peek pede para conferir o número, trocar de transportadora ou excluir a encomenda.
- **Código de 20 dígitos começando por `0039358094`.** Cole normalmente: o Peek acompanha mais de 4.500 transportadoras. Durante a verificação aparece "Consultando serviços de rastreamento…", e a DAI Post está entre as primeiras consultadas. Se tiver o pacote, o Peek a seleciona; se ainda não houver leitura, o pacote exibe "Aguardando a primeira movimentação de DAI Post" e "Provavelmente DAI Post", sugerindo conferir o código com o remetente após uma semana. Para escolher manualmente, toque em "Alterar": ela aparece em "Compatível com este código".
- **Qualquer outro código.** Cole normalmente, toque em "Alterar" e escolha DAI Post (pesquise DAI Post ou Parcel Right). Em pacotes já salvos, use "Alterar transportadora".
- **Link.** Cole um link de rastreio de daipost.com, parcelright.com.au ou parcelrig.ht: o Peek extrai o código e seleciona DAI Post na hora.

O Peek mostra o status, cada linha nas palavras da DAI Post, cidade e país de cada leitura, data prevista de entrega quando informada e horário de entrega. Ele não exibe o ponto de retirada, nome do serviço, peso nem comprovante de entrega. "Abrir o site de DAI Post" abre seu número no rastreador oficial. No código do Peek, alguns envios da [Asendia](carrier:asendia) e alguns da [ePost Global](carrier:epost-global) destinados à Nova Zelândia passam pela DAI Post e exibem suas leituras.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos, e a cada 2 minutos a partir das 6h no horário local no dia em que a DAI Post registra saída para entrega. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". As notificações podem acompanhar "Cada atualização", "Etapas importantes" ou "Somente entrega". O Peek não pode entrar em contato com a DAI Post nem com a loja por você.

## Perguntas sobre o rastreio DAI Post

### Parcel Right é a mesma coisa que DAI Post?

Parcel Right é o serviço próprio de entrega de última milha da DAI Post. No Peek, você pode pesquisar por qualquer um dos dois nomes.

### O que significa "Parcel unsafe to leave at address"?

O motorista não encontrou ninguém e considerou o local inseguro para deixar o pacote. Ele segue para o ponto de retirada mais próximo: retire em até 7 dias ou voltará para a loja.

### Quanto tempo a DAI Post leva para entregar?

Os termos da DAI Post afirmam que os prazos padrão são estimativas, não garantias. O rastreador oficial pode exibir uma data prevista de entrega, que o Peek também mostra.

:::sources
- [DAI Post: Home](https://daipost.com/) – Melbourne, 2000, encomendas dos EUA, Reino Unido, Europa e Ásia para Austrália, Nova Zelândia e Singapura, Parcel Right
- [DAI Post: Help and support](https://daipost.com/help-and-support) – telefones, cobertura, prazos, sem atualizações recentes, notificações, autorização para deixar, retirada, devoluções, retida na alfândega, comprovante de entrega
- [DAI Post: Delivery tracking](https://daipost.com/track) – rastreador, até 10 códigos
- [DAI Post: Contact](https://daipost.com/contact) – formulário de contato e motivos, telefones, consumercare@daipost.com
- [DAI Post: Terms & conditions](https://daipost.com/terms) – local seguro ou Collection Point, prazos estimados, encargos
- [Universal Parcel Scraper: DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – nomes, formatos de código, links, mensagens e etapas, fusos, previsão de entrega
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – detecção, cópias das leituras da DAI Post pela Asendia e ePost Global
:::
