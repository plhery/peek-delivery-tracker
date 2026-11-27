---
title: Rastreio Amazon Logistics: códigos TBA, Lockers e ajuda
description: Acompanhe entregas Amazon Logistics em Seus Pedidos: o que é o código TBA, mapa em tempo real, Amazon Locker e Counter, vendedores e suporte.
slug: amazon-logistics-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Amazon Logistics é a rede de entregas própria da Amazon, formada por empresas parceiras de entrega (*Delivery Service Partners*) e motoristas Amazon Flex que levam os pedidos até sua porta. Você acompanha a encomenda em Seus Pedidos (*Your Orders*) no site da Amazon em que comprou, na opção Rastrear Pacote (*Track Package*).

## Código de rastreio Amazon Logistics

Na Amazon.co.uk, clique em Devoluções e Pedidos (*Returns & Orders*) no canto superior direito. No aplicativo Amazon, toque no ícone de perfil na barra inferior: *Your Orders* lista entregas passadas e futuras. No navegador do celular, toque no ícone de perfil no canto superior direito e em *Track & Manage Your Orders*. Escolha o pedido e toque em *Track Package*.

Quando a encomenda estiver a 10 paradas de distância, a Amazon envia uma notificação push se você ativou os alertas de entrega. Toque nela ou escolha *Track Package* para acompanhar o trajeto em tempo real pelo Amazon Map Tracking e ver quando o motorista está chegando.

Estes são os formatos que o Peek reconhece como Amazon Logistics (dígitos inventados):

| Formato (inventado) | O que é |
| --- | --- |
| `TBA482917360514` | `TBA` seguido de 12 dígitos. |
| `TBC482917360514` | `TBC` seguido de 12 dígitos. |
| `TBM482917360514` | `TBM` seguido de 12 dígitos. |
| `TBAU482917360` | `TBAU` seguido de 9 dígitos: código da Amazon na Austrália. |
| `UK4829173605` | Código de país europeu seguido de 10 dígitos (`UK`, `GB`, `IE`, `FR`, `DE` e outros). |

Dois formatos parecidos que não são Amazon Logistics: um número de pedido como `482-9173605-1482917` não é código de rastreio, e outras duas letras seguidas de 10 dígitos como `US4829173605` também não correspondem à Amazon Logistics. Não tem nenhum código? Veja [como encontrar seu código de rastreio](guide:find-tracking-number).

## Entregas Amazon Logistics em Lockers e Counters

No Reino Unido, você pode enviar um pedido da Amazon para um Amazon Locker ou um Amazon Counter em vez da sua residência.

**Amazon Locker.** Selecione um Locker no checkout buscando por código postal, endereço ou ponto de referência, ou salve um em *Your Addresses*. A Amazon envia por e-mail a confirmação de entrega com as instruções. Você pode precisar do código de seis dígitos, código de barras ou do app Amazon Shopping para abrir o compartimento — leve o celular. Para outra pessoa retirar, basta encaminhar o e-mail. Nem todo produto é elegível: entre outras regras, precisa pesar menos de 4,5 kg, medir menos de 42 × 35 × 32 cm e não ter restrição de idade. Você tem três dias corridos para retirar; após o prazo, a Amazon devolve o pacote e faz o reembolso automaticamente.

**Amazon Counter.** Counters são pontos de retirada com atendimento presencial em lojas parceiras. No checkout, escolha "Find a collect location nearby" para ver Counters e Lockers com seus horários. A Amazon avisa por e-mail quando o pacote chega. Conforme a loja, podem ser exigidos código de retirada, documento com foto e/ou assinatura (o código também consta no rastreio do pedido). Se não retirar no prazo, a Amazon devolve e reembolsa automaticamente.

## Encomenda Amazon Logistics atrasada ou não recebida

**Atrasada ou sumida.** Depois de enviado, não é possível cancelar o pedido, mas você pode recusar a entrega na porta ou devolver após receber. Próximos passos em [rastreio sem atualização](guide:tracking-not-updating), [entregas perdidas](guide:missed-delivery), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

**Vendedores parceiros (Marketplace).** O vendedor que envia por conta própria faz seu próprio atendimento; se o anúncio era elegível ao Prime, a Amazon orienta procurar o atendimento da própria Amazon. Para falar com o vendedor, abra *Your Orders*, clique em *View Your Item* e escolha *Contact Seller*. Vendedores devem responder em até dois dias úteis, com mensagens visíveis na Central de Mensagens (*Message Centre*). Se precisar de reembolso e o vendedor não responder em 48 horas, a maioria dos pedidos Marketplace conta com a Garantia de A a Z (*A-to-z Guarantee*), cobrindo prazo de entrega e estado do produto diretamente com a Amazon. Veja quem entrega cada tipo de pedido em [como rastrear um pedido da Amazon](guide:amazon-orders).

## Telefone e atendimento ao cliente Amazon Logistics

O Atendimento ao Cliente Amazon cuida de dúvidas sobre seus pedidos. Na Amazon.co.uk:

- **Páginas de ajuda:** a página de Atendimento ao Cliente responde dúvidas frequentes, de rastreio à troca de itens danificados.
- **Chat ao vivo:** na página de Atendimento, escolha *Something else* e depois *Contact Us* (no app: *I need more help*) e digite "Chat with an associate now".
- **Telefone:** no mesmo chat, digite "Request a phone call", informe seu número e clique em "Call me now" para a Amazon ligar para você. Atendentes ligam todos os dias das 6h à meia-noite.
- **Redes sociais:** @AmazonHelp responde 24/7 no Facebook, X e Instagram.
- **No Locker:** o telefone direto de suporte vem impresso em cada Amazon Locker.

> Se uma mensagem citar uma compra que você não fez, a Amazon orienta não responder nem clicar em links: verifique seus pedidos direto na conta ou no app. A Amazon não cobra por telefone ou e-mail e nunca pede que você compre vales-presente. Não ligue para telefones recebidos por SMS, e-mail ou resultados de busca. Toda comunicação legítima fica registrada na Central de Mensagens (*Message Centre*), e a Amazon oferece ferramenta própria para denunciar golpes. Leia mais em [golpes por SMS de entrega](guide:delivery-scam-texts).

## Rastreamento Amazon Logistics no Peek

O [Peek](/) não consegue rastrear nem salvar uma entrega Amazon Logistics: a Amazon exibe status, histórico e foto apenas dentro da sua conta. Ao colar um desses códigos ou um link `track.amazon`, o Peek consulta primeiro o rastreador público da Amazon Shipping: se a Amazon responder, trata-se de um envio [Amazon Shipping](carrier:amazon-shipping) (ou compra feita fora da Amazon enviada pela Amazon ao vendedor) e o Peek acompanha normalmente, mantendo a entrega atualizada; se a Amazon não disponibilizar mais o histórico, ele pode ficar salvo apenas como referência.

Caso contrário, o Peek avisa que entregas Amazon Logistics costumam ser acompanhadas em Seus Pedidos na Amazon e exibe um botão para abrir o histórico no site correspondente ao prefixo: amazon.co.uk para `UK` e `GB`, amazon.ie para `IE`, amazon.com para `TBA` e amazon.com.au para `TBAU`. Se a verificação falhar, toque em tentar novamente. Um número de pedido é identificado como número de pedido da Amazon.

## Perguntas sobre a Amazon Logistics

### O que é a Amazon Logistics?

É a rede que realiza o trecho final dos pedidos da Amazon, das estações de entrega até sua porta. No Reino Unido, a Amazon trabalha com *Delivery Service Partners* (pequenas empresas com motoristas próprios e de 20 a 40 vans cada) e motoristas independentes do Amazon Flex. Alguns pedidos seguem por outras transportadoras, como DPD ou Royal Mail.

### A Amazon Logistics é confiável?

Sim, é o sistema próprio da Amazon para entregar seus pedidos, e o envio aparece em *Your Orders*. Já uma mensagem inesperada sobre compra ou entrega que você não reconhece exige cuidado: não clique em links e confira diretamente no app ou site da Amazon.

### Quanto tempo a Amazon Logistics leva para entregar?

*Your Orders* informa o dia previsto de entrega e, quando faltam 10 paradas, você acompanha o motorista no Amazon Map Tracking. Na Irlanda, a Amazon entrega sete dias por semana a partir do centro de distribuição em Baldonnell (Dublin) e das estações de entrega em Rathcoole e Ballycoolin.

### Amazon Logistics é a mesma coisa que a Amazon?

Para você, sim: os motoristas trabalham para parceiros de entrega da Amazon ou pelo Amazon Flex, mas seu relacionamento continua com a Amazon, em *Your Orders* e no Atendimento ao Cliente. Apenas vendedores terceiros que fazem o próprio envio cuidam diretamente do atendimento.

:::sources
- [About Amazon UK: Who delivers my Amazon package in the UK?](https://www.aboutamazon.co.uk/news/operations/how-your-package-gets-from-amazons-warehouse-to-your-front-door) – Delivery Service Partners, Amazon Flex, outras transportadoras
- [About Amazon EU: Delivery and Logistics](https://www.aboutamazon.eu/what-we-do/delivery-and-logistics) – última milha a partir de estações de entrega
- [About Amazon UK: How to track an Amazon package](https://www.aboutamazon.co.uk/news/retail/how-to-track-an-amazon-package) – Your Orders, Track Package, mapa em tempo real
- [About Amazon UK: How to use Amazon Locker](https://www.aboutamazon.co.uk/news/operations/how-to-use-amazon-locker) – escolha de Locker, código, elegibilidade, três dias, reembolso, telefone nos Lockers
- [About Amazon UK: What is an Amazon Counter and how does it work?](https://www.aboutamazon.co.uk/news/retail/how-to-use-amazon-counter) – Counters, código de retirada, documento, itens com restrição etária
- [About Amazon UK: How to cancel an order on Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-cancel-an-amazon-order) – pedidos enviados, recusa na entrega
- [About Amazon UK: How to contact a third-party seller on Amazon](https://www.aboutamazon.co.uk/news/small-businesses/how-to-contact-amazon-seller) – anúncios Prime, dois dias úteis, 48 horas, Garantia de A a Z
- [About Amazon UK: How to contact Amazon UK Customer Service](https://www.aboutamazon.co.uk/news/retail/how-to-contact-amazon-uk-customer-service) – páginas de ajuda, chat, retorno por telefone, horários, @AmazonHelp
- [About Amazon UK: 7 tips to avoid scams impersonating Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-avoid-scams-impersonating-amazon-this-holiday-season) – mensagens falsas, Message Centre, denúncias
- [About Amazon EU: Amazon's €25 billion commitment to Ireland](https://www.aboutamazon.eu/news/job-creation-and-investment/amazons-25-billion-commitment-to-ireland-creating-jobs-driving-growth-and-building-infrastructure) – estações de entrega na Irlanda, sete dias por semana
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek reconhece e verifica
:::
