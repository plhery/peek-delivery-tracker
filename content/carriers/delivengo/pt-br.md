---
title: Rastreio Delivengo: acompanhe encomendas La Poste da França
description: Rastreie encomendas Delivengo enviadas da França: código FR, mensagens da La Poste, quem entrega no seu país e o que fazer se o rastreio parar.
slug: delivengo-tracking
published: 2026-11-27
updated: 2026-11-27
---

O Delivengo é o serviço de exportação da [La Poste](carrier:la-poste) voltado a pequenas encomendas internacionais. Se uma loja francesa o escolheu, seu pacote deixa a França pela La Poste e o correio do seu país realiza a entrega final. Rastreie no portal da La Poste em laposte.fr com o código fornecido pela loja, ou cole-o na caixa acima.

## Seu código de rastreio Delivengo

Os códigos Delivengo seguem o padrão postal internacional: duas letras, nove dígitos e `FR`, como `LD482910576FR` (inventado).

**De onde vem.** A loja informa o código, geralmente no e-mail de envio. O portal MyDelivengo (mydelivengo.laposte.fr) é a área de conta dos lojistas, não um local de consulta para destinatários. Não encontrou o código? Veja [como encontrar seu código de rastreio](guide:find-tracking-number).

**Códigos parecidos** (todos inventados):

| Formato | A quem pertence |
| --- | --- |
| `LD482910576FR` | La Poste: Delivengo, Colissimo e outros objetos postais franceses compartilham esse formato. |
| `LD482910576GB` | O mesmo padrão com letras de outro país pertence ao correio daquele país (neste exemplo, [Royal Mail](carrier:royal-mail)), não ao Delivengo. |

As duas últimas letras indicam qual correio emitiu o código. Nada em um número `…FR` diferencia Delivengo de outro objeto da La Poste, e isso não importa porque o rastreamento é o mesmo. Os códigos têm dígito verificador: um único dígito errado invalida o número e o Peek não o reconhece — prefira copiar e colar.

## Delivengo suivi: o que diz o rastreamento

O rastreamento da La Poste responde a códigos Delivengo da mesma forma que a qualquer outro envio da La Poste. O Peek classifica cada leitura em uma etapa; veja o significado da leitura da La Poste por trás de cada uma:

| Etapa no Peek | O que a leitura da La Poste significa |
| --- | --- |
| `Label created` | Envio anunciado: a loja gerou a etiqueta, mas a La Poste ainda não está com o pacote. |
| `Received by carrier` | A La Poste recebeu o pacote sob seus cuidados. |
| `In transit` | Em movimento pela França, pronto para deixar a França ou já no seu país em trânsito. |
| `At customs` | Entrou na alfândega; após liberação, volta para `In transit`. |
| `Delivered` | Entregue. Se não estiver lá, veja [entregue mas não recebida](guide:delivered-not-received). |
| `Needs attention` | Ocorreu um problema — dano, recusa, endereço errado ou retorno à loja. A leitura da La Poste pode indicar o motivo (endereço incorreto, prazo de retirada vencido ou documentos de importação incompletos). |
| `Returned to sender` | O pacote voltou para a loja. |

Após a exportação, o correio do seu país cuida da entrega final e o histórico da La Poste pode ficar sem atualizações, encerrando na saída da França ou chegada ao destino.

## Entrega e tentativas perdidas

**Quem entrega.** O serviço postal do seu próprio país faz a entrega final, não a La Poste.

**Se você não estiver em casa.** Valem as regras do seu correio local, não as da La Poste: forma de aviso, prazo de guarda e novas tentativas. Veja [entrega perdida](guide:missed-delivery).

**Alfândega.** Dentro da União Europeia — da França para a Irlanda, por exemplo — não há cobrança de tarifas alfandegárias entre países-membros. Fora da UE (como Reino Unido, EUA, Canadá ou Austrália), aplicam-se as regras locais; consulte [encomenda retida na alfândega](guide:customs).

## Encomenda Delivengo atrasada, parada ou perdida

A loja despachou pela conta Delivengo dela, então comece falando com a loja.

**Dias sem atualização.** Após a exportação, o histórico da La Poste pode ficar quieto enquanto o correio local realiza a entrega final. O Peek também consulta os correios de vários países (veja abaixo). Se nada avançar, pergunte à loja. Veja as causas habituais em [rastreio sem atualização](guide:tracking-not-updating).

**Perdida ou danificada.** Peça novo envio ou reembolso à loja. Se você mora na UE, o portal Your Europe esclarece que o comerciante responde por danos do despacho até você receber o produto e, se não chegar em 30 dias (ou no prazo combinado), você deve notificar o vendedor concedendo prazo adicional razoável; persistindo a falta de entrega, pode cancelar o contrato e receber reembolso. Veja [encomenda perdida](guide:lost-parcel).

**Devolvida.** Encomendas que não podem ser entregues retornam à loja: o Peek mostra `Needs attention` no retorno e depois `Returned to sender`. Pergunte à loja se fará novo envio. Veja [devolvida ao remetente](guide:returned-to-sender).

## Contato Delivengo

- **Primeiro, a loja:** foi ela quem postou e detém a conta Delivengo.
- **La Poste, para reclamação:** a Arcep (reguladora postal francesa) informa que reclamações à La Poste podem ser feitas pelo telefone 36 31 (segunda a sexta das 8h30 às 19h, sábado das 8h30 às 13h) ou +33 810 821 821 (segunda a sexta das 8h às 19h), pelo site, por carta ou em um ponto postal. A Arcep lembra que os direitos de remetente e destinatário diferem e não especifica se esses canais atendem pacotes Delivengo.
- **Seu correio local**, assim que o pacote chegar ao seu país, usando o mesmo código.

## Como acompanhar encomendas Delivengo no Peek

O [Peek](/) não tem integração separada para Delivengo: acompanha esses pacotes pelo rastreamento da La Poste, como qualquer envio La Poste ou Colissimo. Cole o código `…FR` e o Peek identifica "La Poste / Colissimo" na hora; se quiser selecionar "Delivengo" em "Alterar", o rastreamento é idêntico. Links de laposte.fr (inclusive de mydelivengo.laposte.fr) também funcionam.

O Peek exibe o que a La Poste publica: status, histórico com o país de cada leitura e data prevista quando disponível, omitindo seu nome e endereço. Os textos permanecem no francês da La Poste, salvo etapas comuns como entrega, traduzidas para o seu idioma.

Quando a La Poste informa o país de destino, o Peek também pode consultar o correio acompanhado naquele país e alternar para ele assim que houver progresso mais recente — incluindo todos os países da UE, Reino Unido, Suíça, Noruega, EUA, Canadá, Austrália e Japão, entre outros. Ainda assim, o histórico da La Poste pode ficar quieto após a exportação e o último trecho nem sempre aparece.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos — ou a cada 2 minutos a partir das 6h no horário local no dia da saída para entrega, enquanto o site da La Poste responder (após o repasse, no ritmo do correio de destino; via serviços agregadores, até a cada 15 minutos nesse dia); nos demais momentos, a cada hora. As notificações cobrem cada leitura, etapas importantes ou somente o dia da entrega. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora.

## Perguntas sobre o rastreio Delivengo

### Delivengo é o mesmo que Colissimo?

Não, embora ambos pertençam à La Poste e compartilhem o mesmo sistema de rastreamento. Delivengo é o serviço de exportação da La Poste para pequenas encomendas internacionais.

### Por que meu rastreio Delivengo parou depois da França?

Depois que o pacote sai da França, o correio do seu país cuida da entrega final e o histórico da La Poste pode ficar sem atualizações, mesmo com o envio seguindo normalmente. O Peek também consulta os correios de diversos países (veja acima); se continuar parado, fale com a loja.

### Posso rastrear um pacote Delivengo para os EUA ou Canadá?

Sim, com o mesmo código `…FR` no rastreamento da La Poste. Assim que a La Poste informa o destino, o Peek consulta também a [USPS](carrier:usps) ou o [Canada Post](carrier:canada-post) e passa a acompanhá-lo lá quando houver avanço mais recente.

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – serviço de exportação da La Poste, última milha pelo correio de destino, silêncio após exportação, área do lojista, sem regra própria de código
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – rastreamento compartilhado, formato e dígito verificador, significado de cada leitura
- [European Union: EU customs union in action](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_en) – ausência de tarifas alfandegárias entre países da UE
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – responsabilidade do vendedor por danos até a entrega, entrega não realizada
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – reclamações à La Poste: telefones, horários, site, carta, ponto postal, direitos de remetente e destinatário
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
