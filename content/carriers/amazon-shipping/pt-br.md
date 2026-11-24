---
title: Rastreamento Amazon Shipping: códigos, problemas e ajuda
description: Rastreie um pacote da Amazon Shipping enviado por outra loja: formato do código, diferença para Amazon Logistics, atrasos e quem contatar.
slug: rastreamento-amazon-shipping
published: 2026-11-24
updated: 2026-11-24
---

A Amazon Shipping é o serviço da Amazon que entrega encomendas para outras empresas, permitindo que um vendedor independente envie seu pedido pela rede da Amazon. Você pode rastrear com o código no rastreador público da Amazon Shipping (track.amazon.co.uk para códigos do Reino Unido), que explica que o vendedor cria a remessa quer você tenha comprado na Amazon.co.uk ou em qualquer outro site. Demais pedidos da própria Amazon são acompanhados em Seus Pedidos (Your Orders).

## Código de rastreamento e ID da Amazon Shipping

O Peek reconhece estes formatos como códigos de rastreamento da Amazon:

| Formato (inventado) | O que é |
| --- | --- |
| `UK4829105736` | Um código de país e 10 dígitos — aqui, do Reino Unido. |
| `FR4829105736` | Outros códigos de países europeus seguem a mesma regra (`FR`, `IT`, `ES`, `DE` e outros). |
| `TBA482910573649` | `TBA`, `TBC` ou `TBM` e 12 dígitos. |

O detalhe: a Amazon Logistics, que entrega os pedidos da própria Amazon, usa exatamente os mesmos formatos. Apenas o rastreador público da Amazon consegue diferenciá-los, respondendo "não encontrado" para pedidos da própria Amazon. Se não encontrar nada e você comprou na própria Amazon, acompanhe pela Amazon Logistics em Seus Pedidos. Um número de pedido como `408-2917364-8291057` não é um código de rastreamento. Sem código? A loja tem o número: veja [como encontrar o código de rastreamento](guide:find-tracking-number).

## Pacote Amazon Shipping não entregue, desaparecido ou avariado

Na Inglaterra, a Citizens Advice recomenda que, em compras online, você tente falar com o vendedor antes de reclamar com a transportadora ou pedir indenização a ela, pois costuma gerar um resultado melhor.

**Desaparecido, ou marcado como entregue sem chegar.** Peça reembolso ou reenvio ao vendedor. Mais detalhes em [entregue, mas não recebido](guide:delivered-not-received).

**Avariado.** Fale com o vendedor e verifique as opções para devolver mercadoria danificada.

## Atendimento e contato da Amazon Shipping

Se o pacote não chegou ou chegou avariado, seu contato é a loja que fez o envio, conforme explicado acima.

> Se uma mensagem mencionar uma compra, a Amazon orienta a não responder nem clicar em links. Ela não pede pagamento por telefone ou e-mail, nunca pede que você compre cartão-presente e recomenda não ligar para números enviados por SMS, e-mail ou achados em buscas. Denuncie qualquer mensagem suspeita usando a ferramenta de autoatendimento da Amazon. Mais em [golpes de SMS de entrega](guide:delivery-scam-texts).

## Como rastrear encomendas da Amazon Shipping no Peek

Cole o código ou um link de track.amazon (`/tracking/` seguido do número) no [Peek](/). O Peek consulta o rastreador público da Amazon para ver se ele reconhece o número. Se a Amazon responder, você verá "A Amazon Shipping oferece rastreamento público. O Peek vai manter esta entrega atualizada." Caso contrário, o pacote conta como entrega da Amazon Logistics, normalmente acompanhada em Seus Pedidos na Amazon. Se a verificação não puder ser feita no momento, tente novamente. A Amazon Shipping não aparece na lista de "Trocar": essa verificação é a única forma de selecioná-la. Não precisa de código postal.

O Peek mostra o status, o histórico com cidade, região e país de cada leitura, o dia previsto de entrega e o horário da entrega, verifica até a cada 10 minutos (até a cada 2 minutos a partir das 6h no horário local no dia em que a Amazon registra saída para entrega), marca cada evento no mapa e pode notificar sobre mudanças. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Ele exibe rótulos curtos no seu idioma em vez das frases completas da Amazon, exceto `Arrived at sorting center`, `Arrived at delivery center` e `Departed facility`, que permanecem em inglês. Omite nome da loja, seu nome e endereço, assinatura e foto da entrega. A Amazon é a única fonte: quando ela deixa de fornecer o histórico, o Peek avisa e para de verificar, sem acompanhar o pacote em outra transportadora.

## Perguntas sobre o rastreamento Amazon Shipping

### A Amazon Shipping é o mesmo que a Amazon?

Não exatamente: é a rede da Amazon entregando para outras empresas, tenha você comprado delas na Amazon.co.uk ou em outro site. Você rastreia em track.amazon.co.uk e fala com o vendedor se houver problema. Outros pedidos da Amazon ficam em Seus Pedidos.

### A Amazon Shipping é confiável?

Sim. É o serviço de entregas da Amazon para outras empresas. Uma mensagem sobre um pedido que você não reconhece é outra história: a Amazon orienta não responder, não clicar nos links nem ligar para telefones indicados nela.

### Quanto tempo a Amazon Shipping leva para entregar?

Quando o rastreador da Amazon informa uma data prevista de entrega, o Peek exibe essa data, exceto em códigos `TBA`, `TBC` e `TBM`.

:::sources
- [Amazon Shipping: Track a Package](https://track.amazon.co.uk/) – pedido feito na Amazon.co.uk ou em qualquer outro site, criação do envio pelo vendedor e serviços para empresas
- [About Amazon UK: Amazon CEO Andy Jassy's 2023 Letter to Shareholders](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – o que é a Amazon Shipping: rede de última milha da Amazon levando encomendas de outras empresas aos clientes delas
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – falar primeiro com o vendedor, reembolso ou reenvio e devolução de mercadoria avariada
- [About Amazon UK: 7 tips to avoid scams impersonating Amazon](https://www.aboutamazon.co.uk/news/retail/how-to-avoid-scams-impersonating-amazon-this-holiday-season) – mensagens sobre compra, pagamento, cartões-presente, números aos quais não ligar e denúncia
:::
