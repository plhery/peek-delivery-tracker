---
title: Rastreio CDEK (СДЭК): números e estados em português
description: Segue uma encomenda CDEK (СДЭК): formato do número de pedido, estados russos explicados em português, entrega por estafeta ou ponto de recolha.
slug: rastreio-cdek
published: 2026-11-22
updated: 2026-11-22
---

A CDEK (СДЭК, por vezes escrita SDEK) é uma transportadora listada no catálogo do Peek para Rússia, Cazaquistão, Bielorrússia e Sérvia. Entrega encomendas em casa por estafeta ou num ponto de recolha ou cacifo. O vendedor ou remetente fornece o número: cola-o na caixa acima. O seguimento da CDEK funciona em my.cdek.ru. A CDEK escreve os registos em russo e a tabela abaixo explica-os em português.

## Número de seguimento CDEK: como é

A CDEK chama pedido (заказ) a cada envio, pelo que o código é um número de pedido CDEK (diferente do número de encomenda da loja). O seguimento aceita códigos de 5 a 40 letras e dígitos: os seus próprios números de 10 ou 11 dígitos e outros códigos associados, como códigos postais internacionais. O Peek associa apenas um formato à CDEK (dígitos inventados):

| Número (inventado) | O que é |
| --- | --- |
| `10482917365` | 11 dígitos começados por `10`: o único formato que o Peek associa à CDEK, apenas como correspondência fraca. [GLS Alemanha](carrier:gls-de), GLS Suíça, GLS França, GLS Áustria, GLS Polónia, PostLogistics, Coordinadora e Mainfreight também usam 11 dígitos. |
| `48291736548` | 11 dígitos não começados por `10`: o Peek não sugere a CDEK; verifica o código ou escolhe CDEK manualmente (abaixo). |
| `RR482917367CN` | Um código postal estrangeiro, aqui da [China Post](carrier:china-post). O Peek segue-o nesse correio; se a CDEK o associou a um pedido, escolhe CDEK para ver os registos dela. |

Não encontras o teu? Vê [onde encontrar um número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento CDEK em português

Cada passo da CDEK tem um código (pelo qual o Peek define o estado) e texto em russo, que o Peek mostra exatamente como foi escrito. A CDEK agrupa registos sob etapas gerais como `В пути`: o Peek mostra a etapa geral apenas quando não há registos detalhados. Por ordem aproximada:

| Texto CDEK | O que significa para ti | O que o Peek mostra |
| --- | --- | --- |
| `Создан` | Criado: o pedido existe na CDEK, mas pode ainda não ter recebido o volume. | «Etiqueta criada» |
| `Назначен курьер` | Um estafeta vai recolher o pedido junto do remetente. | «Etiqueta criada» |
| `Курьер забрал заказ у отправителя` | O estafeta recolheu o pedido junto do remetente. | «Aceite pela transportadora» |
| `Принят на доставку` | Aceite para entrega: a CDEK já tem a encomenda. | «Aceite pela transportadora» |
| `В пути` | A caminho: etapa geral entre a aceitação e o último troço. | «Em trânsito» |
| `Принят СДЭК на территории РФ` | Uma encomenda do estrangeiro entrou na rede CDEK na Rússia. | «Em trânsito» |
| `Отправлен в сортировочный центр` | Enviada para um centro de triagem. | «Em trânsito» |
| `Прибыл в сортировочный центр` | Chegou a um centro de triagem. | «Em trânsito» |
| `Отправлено в г.` + cidade | Enviada para essa cidade. | «Em trânsito» |
| `Отправлен в пункт доставки` | Enviada para o depósito de saída dos estafetas. | «Em trânsito» |
| `Прибыл в пункт доставки` | Chegou ao depósito de entrega. | «Em trânsito» |
| `Отправлен в пункт выдачи` | Enviada para o teu ponto de recolha. | «Em trânsito» |
| `Доставляет курьер` | Em entrega com estafeta (agrupa tentativas). | «Em distribuição» |
| `Выдан курьеру` | Entregue ao estafeta para distribuição; repete-se em cada tentativa. | «Em distribuição» |
| `Курьер не смог доставить заказ` | O estafeta não conseguiu entregar o pedido. | «Tentativa de entrega» |
| `Готов к выдаче`, `Поступил. Заберите заказ` | Pronta: chegou ao ponto de recolha ou cacifo. Já podes levantar. | «Pronta para levantamento» |
| `Вручён` | Entregue ao destinatário. | «Entregue» |
| `Не вручён` | Não entregue: o pedido terminou sem entrega (não levantado ou recusado). | «Problema reportado» |

A CDEK também pode encerrar um pedido como cancelado («Problema reportado») ou parcialmente entregue quando aceitaste parte e recusaste o resto («Entregue» no Peek).

## Como entrega a CDEK: estafeta ou ponto de recolha

Os registos indicam a forma de entrega da encomenda.

**Na morada.** Após chegar ao depósito (`Прибыл в пункт доставки`), sai com estafeta (`Выдан курьеру`). Após uma tentativa falhada, `Выдан курьеру` reaparece na seguinte.

**Ponto de recolha ou cacifo.** Depois de `Отправлен в пункт выдачи`, aguarda `Поступил. Заберите заказ` antes de ir levantar.

A página do pedido em my.cdek.ru mostra o ponto de recolha, morada, contactos, horários e último dia para levantamento. O Peek omite esses elementos, por isso consulta lá. Como o site principal cdek.ru bloqueia consultas automáticas, esta página não dá prazos de entrega, tempos de guarda nem regras de tentativas. Vê [encomendas não levantadas](guide:uncollected-parcel), [entregas falhadas](guide:missed-delivery) e [cacifos de encomendas](guide:parcel-lockers).

**Do estrangeiro.** `Принят СДЭК на территории РФ` indica que a CDEK assumiu uma encomenda estrangeira na Rússia. Sem acesso às páginas oficiais de cdek.ru, esta página não indica regras ou taxas alfandegárias; vê [encomendas retidas na alfândega](guide:customs).

## Encomenda CDEK parada ou que não chega

**Apenas `Создан` ou sem passos.** O vendedor pode ainda não ter entregue o volume ou o código tem gralha. O Peek continua a verificar. Confere dígito a dígito e pergunta à loja se passarem dias sem mudança.

**Dias sem novidades.** Se o registo mais recente tiver quatro dias ou mais antes da distribuição ou do ponto de recolha, o Peek mostra na tua lista o dia do último registo («Sem atualizações desde …») e no ecrã da encomenda «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a CDEK.», apenas nesse aviso com a ligação «Pede ajuda a CDEK» para as FAQ. Vê [seguimento que não atualiza](guide:tracking-not-updating).

**`Курьер не смог доставить заказ`.** Tentativa falhada. Nova tentativa surge de novo como `Выдан курьеру`; se não surgir, fala com a loja.

**`Не вручён`.** O pedido terminou sem entrega. Pergunta à loja por reembolso ou reenvio. Vê [devolvida ao remetente](guide:returned-to-sender).

**Entregue mas não chegou nada.** Confirma se alguém recebeu por ti e contacta a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida.** A loja contratou a CDEK, por isso contacta primeiro a loja. Vê [encomendas perdidas](guide:lost-parcel).

## Apoio ao cliente e contactos CDEK

Como o site principal cdek.ru bloqueia acessos automáticos, esta página não publica telefone nem e-mail: o Peek lista apenas contactos lidos diretamente nas páginas oficiais da transportadora. O seguimento e as páginas de pedido ficam em my.cdek.ru.

Numa compra online, contacta primeiro a loja.

## Seguir uma encomenda CDEK com o Peek

O [Peek](/) segue mais de 4500 transportadoras, mas não consegue confirmar a CDEK apenas pelo formato do número. Cola-o na mesma:

- **11 dígitos começados por `10`.** O Peek consulta cinco transportadoras compatíveis de cada vez e mostra «A verificar serviços de seguimento…». Se pesquisares da Rússia, Cazaquistão, Bielorrússia ou Sérvia, a CDEK vai primeiro; noutros países continua entre as primeiras cinco. Se a CDEK reconhecer o código, o Peek mostra «CDEK tem esta encomenda.». Se várias o conhecerem, escolhes tu; se ainda nenhuma, aparece «ainda não encontrada · o Peek continua a verificar». Também podes tocar em «Alterar» e escolher CDEK em «Corresponde a este número».
- **Outro formato (incluindo 10 dígitos).** Toca em «Alterar» e procura CDEK (СДЭК ou SDEK também funcionam).
- **Uma ligação.** Cola uma ligação de pedido my.cdek.ru (`/lkfl/order/...`) para seguir logo pela CDEK.

Numa encomenda já adicionada usa «Alterar transportadora». Sem transportadora mostra «Ainda sem histórico de seguimento»; sob CDEK mostra «À espera de CDEK» até haver resposta.

**O que o Peek mostra.** O Peek lê o seguimento público da CDEK só com o número, sem conta nem confirmação por telefone: estado, cada passo em russo, cidade e horas exatas (em UTC convertidas para o teu fuso horário, com «Horas no teu fuso horário:» no fim do histórico). As respostas anónimas ocultam o código de levantamento reservado a contas autenticadas, e o Peek omite morada do remetente, dados do ponto de recolha (morada, telefone, horário, prazo), pagamentos, peso, dimensões e data prevista de entrega. «Abrir o site de CDEK» abre o pedido em my.cdek.ru.

**Outro número para o mesmo pedido.** Se seguires um código associado pela CDEK (como um número postal estrangeiro), a CDEK devolve o seu próprio número de pedido, mostrado como «CDEK segue a encomenda com o número».

**Quando a CDEK não responde.** Os sites da CDEK podem bloquear pedidos automáticos. O Peek recorre então a ParcelsApp, Ship24 e 17TRACK (a cópia da ParcelsApp resume por vezes troços sem hora exata) e regressa à CDEK assim que volta a responder.

**Número de outra transportadora.** Se um código de 11 dígitos guardado em CDEK permanecer desconhecido, o Peek pergunta a outras transportadoras compatíveis nos primeiros 30 dias e muda automaticamente («Alterado automaticamente de CDEK») se outra mostrar movimento.

**Encomendas entregues à CDEK.** A Qwintry entrega algumas encomendas à CDEK: quando indica a CDEK e o respetivo número, aparece «Entrega: CDEK» (ou «Anunciada a CDEK, ainda não recebida» antes do primeiro registo CDEK) e o Peek muda para a CDEK assim que tiver um passo pelo menos tão recente. A DobroPost mostra os passos da CDEK no seu seguimento (`DBRPST`). Para códigos postais estrangeiros sob CDEK consultados da Bielorrússia, o Peek também pode consultar a Belpost.

**Verificações e alertas.** Enquanto tiveres o Peek aberto no ecrã — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a CDEK até de 10 em 10 minutos, ou de 2 em 2 minutos a partir das 6h locais no dia em que sai para distribuição; fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora. Só através de serviços de seguimento verifica de hora em hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega se tiveres a encomenda aberta no ecrã ou as notificações ligadas. Escolhe «Todos os registos», «Passos importantes» (recomendado para pontos de recolha, já que «Só a entrega» não avisa quando fica pronta para levantamento) ou «Só a entrega». O Peek não pode contactar a CDEK nem a loja por ti.

## Perguntas sobre a CDEK

### O que é СДЭК em português ou inglês?

CDEK (também escrito SDEK ou CDEK Express). O seu seguimento funciona em my.cdek.ru.

### Como é um número de seguimento CDEK?

O Peek associa 11 dígitos começados por `10`, como `10482917365`, mas outras transportadoras também usam 11 dígitos (e a CDEK também emite números de 10 dígitos).

### O que significa «Поступил. Заберите заказ» na CDEK?

A encomenda chegou ao ponto de recolha ou cacifo e já pode ser levantada: o Peek mostra «Pronta para levantamento». Consulta a morada e o prazo em my.cdek.ru.

### Posso seguir uma encomenda CDEK em português?

Sim. O Peek mostra o estado geral em português (por exemplo «Em trânsito») e mantém cada etapa no texto russo original da CDEK, explicado na tabela acima.

### Porque é que o Peek pede para escolher transportadora para o meu número CDEK?

GLS, PostLogistics e outras transportadoras também usam 11 dígitos. Se mais do que uma reconhecer o código, escolhe CDEK na lista.

:::sources
- [Universal Parcel Scraper: CDEK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cdek) – nomes, países, formato de 11 dígitos (`10`) e 10 dígitos, ligações my.cdek.ru, códigos, textos russos e estados, horas UTC, número canónico e proteção antibot
- [Universal Parcel Scraper: Qwintry](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/qwintry) – seguimento Qwintry indicando CDEK e o seu número
- [Universal Parcel Scraper: DobroPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dobropost) – passos CDEK no seguimento DobroPost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras transportadoras de 11 dígitos, ordem de consulta e serviços de seguimento, cópias ParcelsApp, passagens de entrega e Belpost na Bielorrússia
:::
