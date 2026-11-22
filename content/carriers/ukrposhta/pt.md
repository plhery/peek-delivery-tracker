---
title: Rastreio Ukrposhta: números, estados e levantamento
description: Segue uma encomenda Ukrposhta (Correios da Ucrânia): códigos de 13 dígitos e UA, significado dos estados em inglês, levantamento e envios do estrangeiro.
slug: rastreio-ukrposhta
published: 2026-11-22
updated: 2026-11-22
---

A Ukrposhta (Укрпошта) é o correio nacional da Ucrânia. Podes seguir uma encomenda em track.ukrposhta.ua, na app Ukrposhta ou colando o número na caixa acima. O número vem do remetente: 13 dígitos dentro da Ucrânia, ou duas letras, nove dígitos e duas letras para envios internacionais.

## Número de seguimento Ukrposhta

Os números da Ukrposhta têm dois formatos, e o código doméstico de 13 dígitos termina com um dígito de controlo (módulo 11).

| Formato (inventado) | O que é |
| --- | --- |
| `0548291736543` | Um envio doméstico na Ucrânia: 13 dígitos. |
| `RR482917367UA` | Um envio internacional expedido na Ucrânia: duas letras, nove dígitos, `UA`. |
| `RR482917367US` | Um envio com destino à Ucrânia mantém o código do correio de origem e o respetivo código de país. |

Sem número? Pede ao remetente ou vê [onde encontrar um número de seguimento](guide:find-tracking-number).

[Não confundir](guide:tracking-number-formats): dentro da Ucrânia, as guias da Nova Poshta têm 14 dígitos e muitos códigos da Meest terminam em `G`.

## O que significam os estados de seguimento Ukrposhta

O Peek pede à Ukrposhta os seus textos em inglês. Os habituais, por ordem aproximada:

| Estado Ukrposhta | O que significa para ti |
| --- | --- |
| `Created online. Waiting acceptance` | O remetente [criou a etiqueta online](guide:tracking-statuses); a Ukrposhta ainda não tem o envio. |
| `Registered` | Aceite pela Ukrposhta. |
| `Departed from the Branch` | Saiu da estação onde foi entregue. |
| `Arrived to the Logistics Center` | Em triagem a caminho pela Ucrânia. |
| `Arrival at inward office of exchange` | Um envio do estrangeiro chegou à Ucrânia. |
| `Held at inward office of exchange` | Retido à chegada para alfândega. |
| `Item presented to customs` | Apresentado à alfândega para verificação. |
| `Released from customs for further delivery` | Libertado pela alfândega. |
| `Departure from inward office of exchange` | Passou a alfândega e segue para a tua região. |
| `Item out for physical delivery` | Em distribuição. |
| `Arrived to the Branch` | Na tua estação postal, pronto para levantamento. |
| `Final delivery` | Entregue em mão. |
| `Returned to Sender (Due to Recipient's Refusal)` | Recusado pelo destinatário e devolvido. |
| `Return: Delivered to Sender` | De volta ao remetente: devolvido, não entregue a ti. |

As etapas de devolução começam por `Return:`. Assim que uma linha indica regresso, o Peek mostra uma entrega posterior como «Devolvida ao remetente» e não «Entregue».

## Levantamento na estação e entrega Ukrposhta

As linhas indicam se a encomenda vem para a tua morada (`Item out for physical delivery`) ou aguarda na estação (`Arrived to the Branch`, apresentado no Peek como «Pronta para levantamento»). A app Ukrposhta mostra as estações no mapa com códigos postais, moradas e horários. Confirma na tua estação o prazo de guarda e o documento de identificação pedido.

## Encomenda Ukrposhta atrasada, perdida ou danificada

Se nada mudar durante dias, vê [seguimento que não atualiza](guide:tracking-not-updating); em caso de retenção aduaneira, vê [encomendas retidas na alfândega](guide:customs).

**Perdida ou atrasada no correio internacional.** Segundo as regras da União Postal Universal (UPU), um correio nacional tem de aceitar um pedido de reclamação sobre encomendas ou envios registados ou segurados, feito pelo remetente ou por ti, até seis meses a contar do dia seguinte ao depósito. Embora estas reclamações sejam gratuitas pela regra geral, a Ucrânia reserva-se o direito de cobrar por elas. A indemnização cabe ao remetente, ou a ti mediante renúncia escrita dele. Em compras online que nunca chegaram, contacta primeiro o vendedor.

**Danificada à chegada.** Assinala o dano antes de aceitar o envio. Pelas mesmas regras da UPU, os correios mantêm responsabilidade por danos detetados antes ou na entrega, e o correio que entrega lavra um auto para assinares, entregando-te uma cópia.

## Apoio ao cliente Ukrposhta

Para problemas com a aplicação, a página da Ukrposhta no Google Play indica mobile-app@ukrposhta.ua. Para um envio internacional perdido ou atrasado, apresenta reclamação como descrito acima: segundo as regras UPU, a Ukrposhta deve aceitá-la tanto de ti como do remetente.

## Seguir Ukrposhta com o Peek

O [Peek](/) segue mais de 4500 transportadoras. Para 13 dígitos com dígito de controlo válido da Ukrposhta, o Peek consulta a Ukrposhta — bem como a [Delhivery](carrier:delhivery) se o código também encaixar no formato dela (primeiro dígito 1–9, último 0–6) — e mostra Ukrposhta com «tem esta encomenda» quando só ela o conhece; se ambas o conhecerem, escolhes tu. Um código de 13 dígitos com dígito de controlo errado não é proposto à Ukrposhta: seleciona Ukrposhta em «Alterar». Um código S10 começado por `C`, `E`, `L` ou `R` terminado em `UA` com dígito de controlo válido é identificado de imediato como Ukrposhta; com outra letra inicial válida, o Peek consulta Ukrposhta e [Chronopost](carrier:chronopost), continuando a verificar depois de adicionares se nenhuma o reconhecer ainda. Uma ligação track.ukrposhta.ua com o número (`barcode=`) escolhe sempre Ukrposhta.

Um código postal de outro país não é entregue de início à Ukrposhta. Quando o envio se destina à Ucrânia, o Peek pode acompanhá-lo até à Ukrposhta, ou podes escolher «Ukrposhta» em «Alterar».

O Peek consulta o serviço por trás da app Ukrposhta; se falhar ou não conhecer um envio internacional, abre a página de seguimento da Ukrposhta num navegador no servidor. Mostra cada etapa num mapa com local e país, os passos aduaneiros e a hora de entrega. A Ukrposhta envia horas sem fuso horário: registos na Ucrânia são lidos na hora de Kyiv e convertidos para o teu fuso horário (o histórico termina com «Horas no teu fuso horário:» e o respetivo nome), enquanto linhas de outros países ou sem país (como voos) mantêm a hora local. O Peek não mostra data prevista de chegada e omite data planeada e códigos postais; o único ponto de recolha nomeado é a estação em `Arrived to the Branch`. Os estados mantêm o texto inglês da Ukrposhta, incluindo `Final delivery`.

Enquanto mantiveres a encomenda aberta no ecrã — ou no dia da entrega com as notificações ligadas — o Peek verifica até de 10 em 10 minutos, e já em distribuição, a partir das 6h de Kyiv, de 2 em 2 minutos (ou de 5 em 5 se a última verificação usou navegador), enquanto o serviço ou site responder. Fora disso, e após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora. Só através de serviços de seguimento verifica de hora em hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega se tiveres a encomenda aberta no ecrã ou as notificações ligadas. Escolhe «Todos os registos», «Passos importantes» ou «Só a entrega». Após quatro dias sem novidades, o Peek mostra na tua lista o dia do último registo («Sem atualizações desde …») e no ecrã da encomenda «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Ukrposhta.», com a ligação «Pede ajuda a Ukrposhta» para o centro de contactos apenas nesse aviso.

## Perguntas sobre a Ukrposhta

### Como sigo uma encomenda Ukrposhta no Reino Unido?

Quando adicionas um envio Ukrposhta no Reino Unido, o Peek consulta também a [Royal Mail](carrier:royal-mail) com o mesmo número, ou a Parcelforce se começar por `E` (EMS). Assim que esse correio tiver um registo pelo menos tão recente como o último da Ukrposhta, o Peek prossegue lá mantendo os registos anteriores.

### Porque é que o meu número de seguimento Ukrposhta não funciona?

Confere-o caráter a caráter com o do remetente. Códigos de 13 dígitos terminam num dígito de controlo e o Peek não sugere Ukrposhta se estiver errado. Se o código estiver certo, a Ukrposhta pode ainda não o ter registado: guardado sob Ukrposhta mostra «À espera de Ukrposhta» enquanto o Peek continua a verificar. Se ao fim de dois dias continuar desconhecido sem outra resposta, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.», verificando diariamente e, após uma semana, semanalmente. Se nada mudar em vários dias, fala com o remetente.

### Como sigo uma encomenda enviada do estrangeiro para a Ucrânia?

Cola o código fornecido pelo remetente. O Peek acompanha no correio de origem; quando este indicar a Ucrânia como destino (ou se a adicionares a partir da Ucrânia), o Peek consulta também a Ukrposhta e passa para lá assim que surgir um registo igual ou mais recente, preservando o histórico anterior. Para seguir logo na Ukrposhta desde o início, escolhe «Ukrposhta» em «Alterar». Uma encomenda DHL da Alemanha cujo número comece por `C` é seguida na Meest em vez disso.

:::sources
- [Universal Parcel Scraper: Ukrposhta](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ukrposhta) – formatos e dígitos de controlo, ligações track.ukrposhta.ua, serviço da app e recurso a navegador, textos ingleses, devoluções, hora de Kyiv e linhas no estrangeiro, campos omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras consultadas por número, passagens para a Ukrposhta (e para a Meest em `C` da DHL) e para a Royal Mail ou Parcelforce, formatos Nova Poshta e Meest
- [União Postal Universal: Convention Manual](https://www.upu.int/UPU/media/upu/files/aboutUpu/acts/06-manualsInThreeVolumes/actInThreeVolumesConventionManual202609En.pdf) – reclamações em seis meses, reserva da Ucrânia para cobrança, indemnização ao remetente (ou renúncia escrita) e auto de danos na entrega
- [Google Play: Ukrposhta](https://play.google.com/store/apps/details?id=ua.ukrposhta.android.app) – seguimento na app, mapa de estações com códigos postais, moradas e horários, e-mail de suporte da app
:::
