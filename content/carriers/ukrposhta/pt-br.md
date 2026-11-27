---
title: Rastreamento Ukrposhta: códigos e status na Ucrânia
description: Rastreie encomendas da Ukrposhta (Correios da Ucrânia): códigos de 13 dígitos e UA, significado dos status, retirada na agência e extravios internacionais.
slug: ukrposhta-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Ukrposhta (*Укрпошта*) é o operador postal nacional da Ucrânia. Rastreie sua encomenda no rastreador track.ukrposhta.ua, no aplicativo Ukrposhta ou colando o código na caixa acima. O código é fornecido pelo remetente: 13 dígitos dentro da Ucrânia, ou duas letras, nove dígitos e duas letras para envios de ou para o exterior.

## Código de rastreamento Ukrposhta

Os códigos da Ukrposhta seguem dois formatos, e o de 13 dígitos termina com um dígito verificador.

| Formato (inventado) | O que é |
| --- | --- |
| `0548291736543` | Envio doméstico dentro da Ucrânia: 13 dígitos. |
| `RR482917367UA` | Envio internacional postado na Ucrânia: duas letras, nove dígitos e `UA`. |
| `RR482917367US` | Envio destinado à Ucrânia mantém o código do correio de origem, terminado no código daquele país. |

Sem código? Peça ao remetente ou veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

[Para não confundir](guide:tracking-number-formats): os códigos domésticos da [Nova Poshta](carrier:nova-poshta) na Ucrânia têm 14 dígitos, e muitos códigos da [Meest](carrier:meest) terminam em `G`.

## O que significam os status de rastreamento da Ukrposhta

O Peek solicita à Ukrposhta as descrições em inglês. As mais frequentes, aproximadamente em ordem:

| Status da Ukrposhta | O que significa para você |
| --- | --- |
| `Created online. Waiting acceptance` | O remetente [criou a etiqueta on-line](guide:tracking-statuses). A Ukrposhta ainda não recebeu o objeto. |
| `Registered` | Aceito pela Ukrposhta. |
| `Departed from the Branch` | Saiu da agência onde foi postado. |
| `Arrived to the Logistics Center` | Em triagem a caminho do destino dentro da Ucrânia. |
| `Arrival at inward office of exchange` | Envio vindo do exterior chegou à Ucrânia. |
| `Held at inward office of exchange` | Retido na chegada para análise aduaneira. |
| `Item presented to customs` | Encaminhado à alfândega para verificação. |
| `Released from customs for further delivery` | Liberado pela alfândega. |
| `Departure from inward office of exchange` | Passou pela alfândega e segue para sua região. |
| `Item out for physical delivery` | Saiu para entrega. |
| `Arrived to the Branch` | Chegou à sua agência, disponível para retirada. |
| `Final delivery` | Entregue ao destinatário. |
| `Returned to Sender (Due to Recipient's Refusal)` | Recusado pelo destinatário e em devolução. |
| `Return: Delivered to Sender` | Devolvido ao remetente: retornou, não foi entregue a você. |

Etapas no caminho de volta começam com `Return:`. Assim que uma linha indica devolução, o Peek classifica uma entrega posterior como "Devolvida ao remetente", e não "Entregue".

## Retirada na agência e entrega da Ukrposhta

As linhas indicam se o envio vai até você (`Item out for physical delivery`) ou aguarda retirada na agência (`Arrived to the Branch`, que o Peek exibe como "Pronta para retirada"). O app Ukrposhta localiza agências no mapa, com código postal, endereço e horários. Confirme na sua agência por quanto tempo o objeto fica guardado e qual documento levar.

## Encomenda Ukrposhta atrasada, extraviada ou danificada

Se não houver movimento por vários dias, veja [por que o rastreamento para de atualizar](guide:tracking-not-updating); se estiver retida na alfândega, veja [encomendas retidas na alfândega](guide:customs).

**Extraviada ou atrasada em envios internacionais.** Pelas regras da União Postal Universal (UPU), todo correio nacional deve aceitar um pedido de busca para encomendas ou registrados/segurados, enviada pelo remetente ou por você, em até seis meses a contar do dia seguinte à postagem. Pelas regras da UPU o pedido de busca é gratuito, mas a Ucrânia reserva-se o direito de cobrar taxa. A indenização por extravio ou dano vai ao remetente, ou a você caso o remetente renuncie por escrito. Em compras on-line que não chegaram, contate o vendedor primeiro.

**Danificada na chegada.** Aponte o dano antes de receber o pacote. Pelas mesmas regras da UPU, os correios respondem por danos constatados antes ou no ato da entrega, e quem entrega deve lavrar um termo para você assinar junto, entregando-lhe uma cópia.

## Atendimento da Ukrposhta

Para problemas no aplicativo, a ficha da Ukrposhta no Google Play informa o e-mail `mobile-app@ukrposhta.ua`. Em casos de envio internacional extraviado ou atrasado, faça um pedido de busca conforme descrito acima: pelas regras da UPU, a Ukrposhta deve aceitá-lo tanto de você quanto do remetente.

## Acompanhe a Ukrposhta com o Peek

Para códigos de 13 dígitos com o dígito verificador da Ukrposhta, o [Peek](/) consulta a Ukrposhta — além de FAN Courier ou iMile se o formato também couber nelas — e exibe "Ukrposhta está com esta encomenda." quando só a Ukrposhta reconhece o código; se outra transportadora também o reconhecer, você escolhe entre elas. Um código de 13 dígitos com dígito verificador incorreto não é sugerido para a Ukrposhta: escolha Ukrposhta em "Trocar". Códigos iniciados por `C`, `E`, `L` ou `R`, terminados em `UA` e com dígito verificador válido são identificados como Ukrposhta na hora; códigos `UA` válidos com outra inicial fazem o Peek consultar Ukrposhta e [Chronopost](carrier:chronopost), continuando a verificar após a adição se nenhuma ainda o conhecer. Links de track.ukrposhta.ua contendo o código sempre apontam para a Ukrposhta.

Números postais de outros países não vão direto para a Ukrposhta. Quando o objeto se dirige à Ucrânia, o Peek pode acompanhar a transição para a Ukrposhta, ou você pode escolher "Ukrposhta" em "Trocar".

O Peek lê o serviço usado pelo app da Ukrposhta; quando esse serviço falha ou não encontra um envio internacional, o Peek abre a página de rastreamento da Ukrposhta em navegador no servidor. Exibe cada etapa no mapa com local e país, movimentações aduaneiras e horário de entrega. A Ukrposhta envia horários sem fuso horário: o Peek interpreta etapas na Ucrânia no horário de Kyiv e estima o fuso de etapas no exterior pelo local ou pelas etapas vizinhas (e esse horário pode não ser exato), convertendo tudo para o fuso do seu aparelho. Não mostra data prevista de entrega e omite data planejada e códigos postais; o único ponto de retirada exibido é a agência de `Arrived to the Branch`. Os status preservam o texto em inglês da Ukrposhta, inclusive `Final delivery`.

Com a encomenda aberta, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos. Quando sai para entrega, consulta até a cada 2 minutos — ou a cada 5 minutos se a última consulta usou o navegador no servidor — a partir das 6h no relógio da movimentação mais recente (horário de Kyiv na Ucrânia) enquanto o serviço ou site da Ukrposhta responder. Nos demais casos, e após dois dias sem novidades (ou 12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora e envia notificações quando o status muda.

## Perguntas sobre o rastreamento da Ukrposhta

### Como rastrear uma encomenda da Ukrposhta no Reino Unido?

Quando você adiciona um envio da Ukrposhta estando no Reino Unido, o Peek também consulta o [Royal Mail](carrier:royal-mail) com o mesmo código, ou a [Parcelforce](carrier:parcelforce) para códigos iniciados por `E` (EMS). Assim que esse correio registrar uma movimentação ao menos tão recente quanto a última da Ukrposhta, o Peek continua acompanhando por lá mantendo as etapas da Ukrposhta.

### Por que meu código da Ukrposhta não está funcionando?

Compare caractere por caractere com o código fornecido pelo remetente. Um código de 13 dígitos termina em um dígito verificador, e o Peek não envia à Ukrposhta um número com dígito verificador inválido. Se o código estiver certo, a Ukrposhta pode ainda não tê-lo registrado: um envio salvo como Ukrposhta exibe "Aguardando Ukrposhta" enquanto o Peek continua verificando. Se dois dias depois a Ukrposhta ainda não reconhecer o código e nenhuma outra fonte responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar diariamente — e semanalmente após uma semana da adição. Se nada mudar em vários dias, consulte o remetente.

### Como rastrear uma encomenda enviada do exterior para a Ucrânia?

Cole o código fornecido pelo remetente. O Peek acompanha no correio de origem. Quando esse correio informa destino Ucrânia, ou você adiciona o código estando na Ucrânia, o Peek passa a consultar também a Ukrposhta e, assim que ela tiver uma movimentação ao menos tão recente quanto a última do correio de origem, prossegue lá preservando o histórico anterior. Para seguir pela Ukrposhta desde o início, escolha "Ukrposhta" em "Trocar". Uma encomenda DHL da Alemanha cujo código começa com `C` é seguida pela [Meest](carrier:meest).

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – formatos de código e dígitos verificadores, links de track.ukrposhta.ua, o serviço do app e o rastreador que o Peek lê, as linhas em inglês e seus status, linhas de devolução, horário de Kyiv e etapas no exterior, o que o Peek omite
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras que o Peek consulta pelo código, correio nacional da Ucrânia, passagens para a Ukrposhta (para a Meest nos códigos C da DHL) e dela para Royal Mail ou Parcelforce, códigos da Nova Poshta e da Meest
- [Universal Postal Union: Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – pedidos de busca em até seis meses, direito da Ucrânia de cobrar por eles, indenização ao remetente ou, por renúncia escrita, a você, dano constatado na entrega e seu termo
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – o app rastreia encomendas e localiza agências com código postal, endereço e horários; e-mail para problemas no app
:::
