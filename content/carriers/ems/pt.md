---
title: Rastreio EMS: códigos S10, estados e apoio postal
description: Acompanha uma encomenda internacional EMS (Express Mail Service): códigos S10 de 13 carateres com E, estados de alfândega e entrega UPU e apoio postal.
slug: rastreio-ems
published: 2026-11-24
updated: 2026-11-24
---

O **EMS** (*Express Mail Service*) é o serviço postal expresso internacional para documentos e mercadorias. A **União Postal Universal (UPU)** criou a **Cooperativa EMS** ([ems.post](https://www.ems.post/en)) para o desenvolver; em junho de 2026 a Cooperativa contava com 179 membros. Em vez de operar aviões ou carrinhas próprias, o EMS liga os operadores postais nacionais designados em todo o mundo — como [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) e [Australia Post](carrier:australia-post) — para que documentos e mercadorias urgentes circulem sob um identificador normalizado de 13 carateres. Podes consultar um envio no rastreio global EMS em [items.ems.post](https://items.ems.post/) ou colar o teu código EMS na caixa acima.

## Números de rastreio EMS (formato UPU S10 de 13 carateres)

Cada envio EMS possui um código de barras UPU S10 de 13 carateres que começa pela letra **`E`** (de Express), seguida por uma segunda letra de serviço, 8 dígitos de série, 1 dígito de controlo S10 e o código ISO de 2 letras do país de origem:

| Formato (inventado) | O que é |
| --- | --- |
| `EA482917367JP` | Envio EMS de 13 carateres expedido do Japão (prefixo `EA` + 9 dígitos com dígito de controlo S10 válido + `JP`). |
| `EE739184627CN` | Envio EMS de 13 carateres expedido da China (prefixo `EE` + 9 dígitos com dígito de controlo S10 válido + `CN`). |

As duas últimas letras identificam sempre o **operador postal de origem**, nunca o país de destino. Uma encomenda enviada por EMS de Tóquio para Paris termina em `JP` durante todo o percurso, mesmo depois de [La Poste](carrier:la-poste) assumir a entrega final em França. Números de envios postais que não começam por `E` (como correio registado `RR...` ou encomendas normais `CP...`) são rejeitados pelo rastreio EMS por não representarem um envio EMS. Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio EMS

A tabela pública de rastreio da Cooperativa EMS em [items.ems.post](https://items.ems.post/) traduz os eventos EDI dos operadores postais participantes em descrições normalizadas em inglês:

| O EMS indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Posted` | Aceite ao balcão ou recolhido pelo operador postal de origem. | «Aceite pela transportadora» |
| `Arrived at export office`, `Departed from export office` | Processado no centro de permuta internacional do país de origem e expedido para o estrangeiro. | «Em trânsito» |
| `Presented to export customs and security`, `Held for export customs inspection` | Em controlo aduaneiro de exportação ou inspeção de segurança aérea no país de origem. | «Na alfândega» |
| `Released from export customs and security` | Controlo de exportação concluído e pronto para expedição internacional. | «Em trânsito» |
| `Export cancelled` | A expedição de exportação foi cancelada ou devolvida antes de sair do país de origem; contacta o remetente. | «Problema reportado» |
| `Arrived at destination import office`, `Departed from destination import office` | Chegou ao centro de permuta internacional do país de destino ou saiu de lá para triagem nacional. | «Em trânsito» |
| `Held at destination import office`, `Presented to import customs` | Retido no centro de permuta fronteiriço ou apresentado à alfândega de importação no destino. | «Na alfândega» |
| `Released from import customs` | Desalfandegado no destino e entregue à rede postal nacional. | «Em trânsito» |
| `Arrived at sorting center`, `Arrived at post office` | Em circulação pelos centros de triagem nacionais dos correios de destino ou chegou à estação de correios local de entrega. | «Em trânsito» |
| `Out for delivery` | Em distribuição com o carteiro local ou estafeta EMS para entrega final. | «Em distribuição» |
| `Delivery attempted` | Tentativa de entrega sem sucesso; verifica um aviso de entrega ou comunicação da estação de correios local. | «Tentativa de entrega» |
| `Returned to sender` | Em devolução para o país de origem após falha na entrega, recusa ou rejeição aduaneira. | «Devolvida ao remetente» |
| `Delivered` | Entregue ao destinatário ou num ponto de levantamento autorizado pelo operador de destino. | «Entregue» |

## Como funcionam as entregas internacionais e a alfândega no EMS

Como o EMS é uma rede cooperativa de correios nacionais listada no [diretório de operadores EMS](https://www.ems.post/en/global-network/ems-operators), duas organizações postais distintas tratam de cada envio transfronteiriço:

- **Correios de origem vs. correios de destino:** o operador postal de origem trata da aceitação, desalfandegamento de exportação e expedição internacional; assim que surge `Arrived at destination import office`, o teu operador postal nacional (ou o seu ramo expresso, como a Parcelforce no Reino Unido) controla o desalfandegamento de importação, as tentativas de entrega, a nova entrega e os prazos de guarda para levantamento.
- **Consulta o EMS e os teus correios locais:** o feed global EMS transmite marcos EDI normalizados, enquanto a página de rastreio dos correios de destino acrescenta muitas vezes detalhes nacionais úteis, como ligações para pagamento aduaneiro, morada da estação de levantamento, janelas horárias ou agendamento de nova entrega.
- **Direitos aduaneiros e IVA de importação:** as encomendas internacionais EMS estão sujeitas às regras aduaneiras do destino, ao IVA de importação, às taxas postais de desalfandegamento e a direitos aduaneiros (incluindo encargos da UE sobre importações extracomunitárias). Se o rastreio parar em `Presented to import customs` ou `Held at destination import office`, aguarda uma carta, e-mail ou aviso oficial no portal dos teus correios locais. Consulta [encomendas retidas na alfândega](guide:customs).

## Encomenda EMS atrasada, parada ou extraviada

- **Pausa entre exportação e importação:** envios aéreos internacionais passam frequentemente vários dias sem registo entre `Departed from export office` e `Arrived at destination import office`, sobretudo em épocas altas ou em fila de admissão aduaneira. Consulta [por que o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Quem ajuda são os teus correios locais, não a UPU:** conforme indicado em [EMS Tracking](https://www.ems.post/en/global-network/tracking), a Unidade EMS e a UPU não dão rastreio por telefone nem intervêm em litígios entre operadores postais e clientes. Para um envio EMS desaparecido, a [página de contactos EMS](https://www.ems.post/en/about-us/contacts-0) encaminha para o apoio ao cliente ou call center EMS do teu país, que esclarece questões gerais sobre o EMS, ajuda a obter o número de rastreio e colabora na localização do envio. Consulta [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente EMS e diretório oficial de operadores

Porque o serviço EMS de cada país é operado pelo respetivo operador postal nacional designado, [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) publica um diretório pesquisável de A a Z com as páginas de apoio ao cliente e centros de atendimento EMS nacionais filtrados por região (África, Países Árabes, Ásia-Pacífico, Caraíbas, Europa, América Latina e América do Norte):

- **Encontrar telefone e apoio do teu operador EMS local:** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) e [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Rastreio público global:** [items.ems.post](https://items.ems.post/) (instruções em [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **Sem apoio telefónico central da UPU:** nem a Unidade EMS nem a União Postal Universal resolvem pedidos sobre encomendas individuais por telefone.

> Tem cuidado com SMS falsas que dizem que uma «encomenda EMS» está bloqueada até pagares uma pequena taxa numa ligação desconhecida. Confirma sempre qualquer taxa aduaneira diretamente no site oficial do teu operador postal nacional com o teu código EMS de 13 carateres. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda EMS com o Peek

Quando introduzes um código S10 de 13 carateres iniciado por `E` (`EA482917367JP` ou `EE739184627CN`, inventados) no [Peek](/), o Peek encaminha-o normalmente primeiro para o operador postal de origem identificado pelas duas últimas letras (como [Japan Post](carrier:japan-post) ou [China Post](carrier:china-post)), porque o EMS não tem uma regra autónoma de deteção automática. Para consultar antes o rastreio global da Cooperativa **EMS**, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda e seleciona **EMS** (pesquisar «Express Mail Service» ou «EMS Post» também o encontra). O Peek reconhece ainda diretamente como EMS ligações coladas de `items.ems.post/api/publicTracking/track?itemId=`.

O Peek consulta diretamente `items.ems.post` depois de confirmar que o código é um número S10 em `E` com dígito de controlo válido, e mostra a etapa («Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Tentativa de entrega», «Problema reportado», «Devolvida ao remetente», «Entregue»), o texto EMS em inglês e a estação postal. O EMS fornece horas de registo sem fuso horário: o Peek data cada registo no fuso horário da respetiva estação (Tóquio para `TOKYO INTERNATIONAL`, Paris para `ROISSY CDG`), e um registo sem local no fuso do registo vizinho, apresentando tudo na hora do teu dispositivo. A tabela EMS não inclui data estimada de entrega, peso, nome de quem assinou nem hora de entrega separada.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que o EMS mostra `Out for delivery`, de 2 em 2 minutos a partir das 6h00 na hora local do lugar do registo mais recente, enquanto o rastreio EMS responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio EMS não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a EMS.», com uma ligação «Pede ajuda a EMS» para o diretório de contactos EMS. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a EMS.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode desalfandegar encomendas, pagar encargos de importação nem contactar os teus correios locais por ti.

## Perguntas sobre o rastreio EMS

### Como é um número de rastreio EMS válido?

Um número de rastreio EMS é um código UPU S10 de 13 carateres iniciado por `E`, uma segunda letra, 9 dígitos (terminando num dígito de controlo) e o código ISO de 2 letras do país remetente — por exemplo `EA482917367JP` ou `EE739184627CN` (inventados).

### Quem entrega a minha encomenda EMS no meu país?

As encomendas EMS são entregues pelo operador postal nacional designado do teu país (ou pela sua unidade expresso), como [USPS](carrier:usps) nos Estados Unidos, [Royal Mail](carrier:royal-mail) / Parcelforce no Reino Unido, [La Poste](carrier:la-poste) em França, [Poste Italiane](carrier:poste-italiane) em Itália ou [Australia Post](carrier:australia-post) na Austrália.

### Por que deixou a minha encomenda EMS de atualizar após `Departed from export office`?

Depois de `Departed from export office`, a encomenda segue em trânsito aéreo internacional ou aguarda entrada no centro de permuta postal internacional do país de destino. O rastreio retoma assim que os correios de destino registam `Arrived at destination import office`.

### Quem devo contactar se uma encomenda EMS estiver retida ou perdida?

A UPU e a Unidade EMS não fazem rastreio por telefone nem intervêm em litígios entre operadores postais e clientes. Utiliza [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) para encontrar o serviço de apoio ao cliente ou call center EMS do teu país, que pode ajudar a localizar o envio.

:::sources
- [Cooperativa EMS: site oficial](https://www.ems.post/en) – apresentação da Cooperativa Express Mail Service da UPU e rede postal mundial
- [Cooperativa EMS: guia de rastreio](https://www.ems.post/en/global-network/tracking) – formato S10 de 13 carateres, não intervenção da UPU/Unidade EMS e ausência de rastreio telefónico
- [Cooperativa EMS: contactos globais de operadores](https://www.ems.post/en/about-us/contacts-0) – diretório A–Z dos serviços de apoio EMS nacionais por região (URL de ajuda ligado pelo Peek)
- [Cooperativa EMS: operadores A–Z](https://www.ems.post/en/global-network/ems-operators) – rede de operadores postais nacionais designados
- [Portal público de rastreio EMS](https://items.ems.post/) – pesquisa pública mundial de envios EMS
- [Universal Parcel Scraper: ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – validação do dígito de controlo S10 com prefixo `E`, regra de ligação `items.ems.post`, mapa de 18 estados da Cooperativa EMS e datação de cada registo no fuso horário da respetiva estação
:::
