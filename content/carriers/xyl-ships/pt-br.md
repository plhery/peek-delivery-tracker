---
title: Rastreamento XYL-Ships: códigos NUDE e XLWL e status
description: Rastreie um pacote da XYL-Ships (FXYL) da China: formatos NUDE e XLWL, significado de cada status no trânsito e na entrega, pausas sem atualização e quem contatar.
slug: rastreamento-xyl-ships
published: 2026-11-24
updated: 2026-11-24
---

A XYL-Ships (XYL-ships International Logistics, também vista como FXYL) é uma provedora chinesa de logística internacional com qualificações de agência de vendas de carga aérea reconhecidas pela Administração de Aviação Civil da China (CAAC) e pela IATA. Além de frete aéreo, marítimo, ferroviário e rodoviário comercial, movimenta pequenos pacotes individuais de e-commerce internacional da China para o exterior e os entrega a parceiros postais ou transportadoras locais para a etapa final. Você pode consultar um pacote pela opção "Track Order" em [xylships.com](https://xylships.com/) ou colar o código de rastreamento na caixa acima.

## Códigos de rastreamento da XYL-Ships: formatos NUDE e XLWL

Procure o código de rastreamento no e-mail de envio da loja online ou na página de histórico de pedidos. Pequenos pacotes da XYL-Ships costumam trazer um código alfanumérico de 16 ou 17 caracteres começando com `NUDE` ou `XLWL` e terminando em `EX` ou `EXP`:

| Formato (inventado) | O que é |
| --- | --- |
| `NUDE4829173640EX` | `NUDE`, 10 dígitos e `EX`. |
| `XLWL4829173640EX` | `XLWL`, 10 dígitos e `EX`. |
| `XLWL4829173640EXP` | `XLWL`, 10 dígitos e `EXP`. |

Como esses códigos são exclusivos da XYL-Ships, eles não se confundem com códigos de barras postais nacionais. Se a loja lhe enviou um código de rastreamento postal internacional S10 (duas letras, nove dígitos e duas letras de país, como `CN`) ou um código específico de outra transportadora, confira [formatos de código de rastreamento](guide:tracking-number-formats) para ver qual empresa o emitiu.

## O que significam os status da XYL-Ships

A XYL-Ships publica seus eventos de rastreamento em inglês, acompanhando o pacote desde a triagem de origem na China, pelo transporte internacional e até o parceiro local de distribuição no país de destino:

| A XYL-Ships mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipment inbound in sorting center` | A XYL-Ships recebeu e bipou o pacote em seu centro de triagem de origem. | "Em trânsito" |
| `Departed from sorting center`, `Arrived at sorting warehouse`, `Leaving warehouse` | Circulando pelos armazéns de triagem na origem antes do repasse para o transporte internacional. | "Em trânsito" |
| `Hand over to transportation company`, `Departed from origin transportation center`, `Arrived at destination transportation center` | Entregue à transportadora internacional de longa distância, saiu da China e chegou ao centro de entrada no país de destino. | "Em trânsito" |
| `Released and the package is being transferred`, `Package is being transferred locally` | Liberado no país de destino e seguindo na transferência doméstica local. | "Em trânsito" |
| `Transfer again, send to the transportation partner department`, `Arrival at transportation partner department, waiting for processing`, `Leaving transportation partner department, sent to the transit hub` | Transferido ao setor de processamento da parceira local de entrega e despachado para seu hub de trânsito. | "Em trânsito" |
| `Arriving at the transit hub, waiting for distribution`, `Leave the transit hub, transport to the destination city`, `Arriving at the destination city, about to distribute to the post office` | Triado no hub regional e transportado até sua cidade de destino para repasse local. | "Em trânsito" |
| `The courier has received this parcel for delivery and will set a delivery time shortly` | O entregador local já está com o pacote, mas a rota de entrega ainda não começou. | "Em trânsito" |
| `Parcel currently being delivered` | Saiu hoje na rota do entregador local. | "Saiu para entrega" |
| `Delivered` | Registrado como entregue no seu destino. | "Entregue" |

Atenção a duas linhas fáceis de interpretar errado perto do fim do trajeto. `Arriving at the destination city, about to distribute to the post office` significa que o pacote está sendo repassado à rede postal ou parceira local, não que esteja esperando você ir buscar no balcão dos correios. Da mesma forma, `The courier has received this parcel for delivery and will set a delivery time shortly` segue em "Em trânsito" porque a rota de entrega ainda não começou — aguarde `Parcel currently being delivered` para esperar a chegada naquele dia.

## Como funciona a entrega internacional da XYL-Ships

Em [xylships.com](https://xylships.com/), a XYL-Ships apresenta frete aéreo comercial (incluindo frete aéreo doméstico premium de 6 e 12 horas e consolidação porta a porta FBA), consolidação marítima em contêiner, transporte ferroviário e rodoviário, além do envio internacional de pequenos pacotes individuais de e-commerce — citando um caso de cliente de pequenos pacotes individuais com 99% de conclusão em até 20 dias.

**Entrega local na última milha.** Como mostram os próprios eventos da XYL-Ships, assim que chega ao país de destino o pacote é repassado a uma parceira local de transporte ou aos correios para entrega final.

**Regras de entrega e dados ausentes.** `xylships.com` não publica número de tentativas de entrega ao destinatário, regras de armários, prazos de guarda, garantias de prazo nem janelas de reclamação. Depois que o pacote entra no seu país, os procedimentos de entrega (deixar com vizinho, local seguro ou dias de guarda após aviso) dependem dos correios ou da transportadora local que concluir a entrega.

**Impostos de importação e alfândega.** Como os envios da XYL-Ships saem da China, encomendas que entram na UE, no Reino Unido, nos EUA ou em outras regiões seguem as regras locais de IVA e alfândega. Para envios destinados à União Europeia, vale o imposto aduaneiro de € 3 por tipo de produto em pacotes de até € 150 e a taxa de manuseio da UE de € 2 por tipo de produto qualquer que seja o valor. Veja [encomendas paradas na alfândega](guide:customs) para entender o impacto da fiscalização aduaneira.

## Pacote XYL-Ships parado, sem movimento ou não recebido

Quando você compra em uma loja online que envia via XYL-Ships, o vendedor é a parte contratual e o contato principal para atrasos, reembolsos ou reenvios.

**Longas pausas no trânsito.** Intervalos de vários dias são comuns entre `Departed from origin transportation center` e `Arrived at destination transportation center` enquanto os pacotes aguardam voos, liberação alfandegária e leitura de entrada no destino. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Sem código local no Peek.** O Peek acompanha o pacote pela parceira local usando o próprio número `NUDE` ou `XLWL` e não exibe um código local separado. Se uma entrega falhar e você precisar do nome ou código da parceira local, pergunte ao vendedor.

**Marcado como `Delivered`, mas não encontrado.** Verifique primeiro caixa de correio, entrada, portaria do prédio e vizinhos. Se ainda não aparecer, avise o vendedor imediatamente e peça comprovante de entrega. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Avariado, extraviado ou devolvido.** Guarde fotos da embalagem danificada e fale com a loja de imediato, ou abra disputa de proteção ao comprador no marketplace ou meio de pagamento antes que o prazo do pedido termine. Veja [encomenda avariada](guide:damaged-parcel), [encomenda extraviada](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Contato da XYL-Ships (e por que falar primeiro com o vendedor)

`xylships.com` é um site voltado a fretes corporativos, com poucas informações de suporte ao consumidor final:

- **E-mail:** `fzxyl9527@163.com` (único contato listado no rodapé de [xylships.com](https://xylships.com/)).
- **Telefone, endereço físico e portal de reclamações:** não publicados em `xylships.com`.

Como a XYL-Ships atende empresas remetentes e não destinatários individuais, contate primeiro a loja online ou o marketplace em caso de extravio, erro de endereço ou reembolso — o comerciante tem acesso direto à conta de frete e aos canais de proteção ao comprador.

> Desconfie de SMS ou e-mails inesperados pedindo que você clique em um link de pagamento para "liberar" ou "reagendar" um pacote da XYL-Ships. Confirme qualquer cobrança alfandegária real diretamente nos correios ou na autoridade aduaneira do seu país, nunca por uma mensagem de texto não verificada. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Como rastrear um pacote da XYL-Ships no Peek

O [Peek](/) reconhece códigos `NUDE` (`NUDE` + 10 dígitos + `EX`) e `XLWL` (`XLWL` + 10 dígitos + `EX` ou `EXP`) como XYL-Ships automaticamente, com alta confiança. Como a XYL-Ships não usa URLs próprias de rastreamento no Peek, cole o código em si em vez de um link; se precisar escolher a transportadora manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote e escolha XYL-Ships (buscar por "FXYL" também encontra).

O Peek lê diretamente o feed JSON de rastreamento da XYL-Ships e mostra a etapa ("Em trânsito", "Saiu para entrega" ou "Entregue"), os textos em inglês da XYL-Ships, a localidade da leitura quando informada e horários com deslocamento explícito de fuso horário (exibidos no fuso do seu aparelho). O Peek omite endereços de remetente e destinatário e referências internas do cliente, e não mostra data prevista, peso, número da transportadora local nem foto de entrega.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos; no dia em que a XYL-Ships mostra `Parcel currently being delivered`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente da XYL-Ships, enquanto o rastreador da XYL-Ships responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, verifica de hora em hora qualquer que seja a forma de acompanhar. Quando o rastreador da XYL-Ships não responde e o Peek recorre apenas a serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia em que sai para entrega enquanto o pacote estiver aberto ou com alertas ligados.

Quando o registro mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página do pacote então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando XYL-Ships." Após três semanas sem novidade, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando XYL-Ships."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas monitora o rastreamento e não pode informar a transportadora local nem falar com a XYL-Ships por você.

## Perguntas sobre o rastreamento XYL-Ships

### O que é a XYL-Ships (FXYL)?

A XYL-Ships International Logistics é uma agente de cargas chinesa e operadora de logística internacional de e-commerce com qualificações de agência aérea da CAAC e da IATA. Lojas a usam para despachar pequenos pacotes da China até entregá-los a transportadoras locais no seu país.

### O que significam códigos `NUDE...EX` e `XLWL...EX`?

Ambos os prefixos são formatos da XYL-Ships: `NUDE` ou `XLWL` seguidos de 10 dígitos e `EX` (ou `EXP` em alguns pacotes `XLWL`). Cole o código completo no Peek ou na opção Track Order em [xylships.com](https://xylships.com/).

### Por que meu rastreamento da XYL-Ships diz `about to distribute to the post office`?

Significa que o pacote chegou à sua cidade de destino e está sendo repassado aos correios ou parceira local para a entrega final. Aguarde `Parcel currently being delivered` para contar com a entrega naquele dia.

### Quem entrega pacotes da XYL-Ships no meu país?

A XYL-Ships transfere os pacotes para uma parceira local de transporte ou para os correios após a entrada no país de destino, e seus registros não informam o nome dessa transportadora; o Peek não mostra um segundo número de rastreamento. Se a entrega falhar, peça ao vendedor os dados da transportadora local.

:::sources
- [XYL-Ships International Logistics](https://xylships.com/) – qualificações de agência aérea CAAC e IATA, frete aéreo/marítimo/ferroviário/rodoviário e pequenos pacotes internacionais de e-commerce, acesso Track Order e e-mail `fzxyl9527@163.com`
- [Universal Parcel Scraper: xyl-ships](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/xyl-ships) – detecção dos formatos `NUDE` e `XLWL`, alias FXYL, integração com o rastreador JSON, mapeamento dos status em inglês, campos omitidos e frequência de atualização
:::
