---
title: Seguimento STO Express: números 申通快递, estados e contacto
description: Acompanha uma encomenda STO Express (申通快递) na China: formato dos números, significado de 签收 e dos registos STO, estações, cacifos e contacto.
slug: seguimento-sto-express
published: 2026-11-22
updated: 2026-11-22
---

A STO Express (申通快递, Shentong) é uma transportadora expresso chinesa fundada em 1993 que se apresenta como a primeira empresa privada de entregas expresso da China. Um número STO pertence normalmente a uma encomenda dentro da China, muitas vezes uma compra online dirigida a ti, a um conhecido ou ao armazém de um transitário. Acompanha-a no [site da STO](https://www.sto.cn/pc), na caixa `查快递` (apenas em chinês), ou na caixa acima.

## Número de seguimento STO Express: como é

Os números da STO (`快递单号`) têm apenas dígitos. A pesquisa STO do Peek aceita 12, 13 ou 15 dígitos; as guias mais recentes têm 15. Com dígitos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `774829173654281` | `77` e mais 13 dígitos: uma guia da STO Express. A [BRT](carrier:brt) e a Yunda também usam 15 dígitos, pelo que o Peek confirma com a STO. |
| `482917365420` | 12 dígitos: um formato antigo da STO, partilhado por [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) e muitas outras. |
| `4829173654281` | 13 dígitos: outro formato antigo da STO, que o Peek não associa automaticamente à STO. |

Os espaços não importam no Peek.

**Onde encontrar.** O vendedor indica-o nos dados de envio do pedido e aparece na etiqueta (`快递面单`) colada na encomenda. Se não o encontrares, vê [como encontrar o teu número de seguimento](guide:find-tracking-number).

**No site da STO.** Escreve o número na caixa `查快递` em `sto.cn` e carrega em `查询` (pesquisar). O acordo de serviço da STO refere que o site, a app e a conta WeChat mantêm o seguimento durante um ano a partir do dia de envio.

## Estados de seguimento da STO Express explicados

Cada registo no seguimento da STO inclui um tipo em chinês, uma hora na hora da China (UTC+8) e a instalação STO que fez a leitura. O Peek mostra a maioria destes tipos em inglês:

| Registo STO | No Peek | Estado no Peek | O que significa para ti |
| --- | --- | --- | --- |
| `收件` | `Picked up` | Aceite pela transportadora | A STO recolheu a encomenda ao remetente. |
| `发件` | `Departed`, com `for` e a instalação seguinte quando a STO a indica | Em trânsito | Saiu de uma instalação STO rumo à seguinte. |
| `到件` | `Arrived` | Em trânsito | Chegou a um centro de triagem ou agência. |
| `派件` | «Em distribuição» | Em distribuição | Um estafeta está na rota com ela. |
| `驿站代收` | `At a parcel station for collection` | Pronta para levantamento | Uma estação de encomendas (`驿站`) recebeu-a por ti. Vai levantá-la. |
| `柜机代收` | `In a parcel locker for collection` | Pronta para levantamento | Está num cacifo automático. Vai levantá-la. |
| `客户签收` | «Entregue» | Entregue | Alguém assinou. A STO também regista isto quando uma estação ou cacifo assina pela encomenda. |
| `签收` | «Entregue» | Entregue | Assinada. |

Outros tipos de registo mantêm no Peek o texto chinês da STO. Um tipo que mencione devolução (`退回` ou `退件`) aparece como `Return to the sender started` («Problema reportado»). Os registos de entrega seguintes correspondem ao regresso: `Out for delivery back to the sender`, `In a parcel locker or station on its way back` e `Returned to the sender`.

**Enviada para um transitário?** Então `签收` significa que o armazém do transitário assinou a receção e a parte da STO terminou. A partir daí entra o número próprio do transitário.

## Entrega STO Express na China: estafetas, estações e cacifos

A STO afirma que a sua rede cobre 99,7 % dos municípios chineses. O acordo de serviço permite entregar uma encomenda de quatro formas:

- a ti pessoalmente;
- a alguém que viva contigo ou a uma pessoa designada por ti;
- num ponto de recolha (`代收点`) ou cacifo (`自提柜`) na zona da morada;
- de outra forma solicitada por ti ou pelo remetente.

Deixada a encomenda por uma destas vias com a embalagem exterior intacta, o acordo considera concluído o serviço da STO. Assim, «Pronta para levantamento» no Peek é normal: a encomenda aguarda numa estação ou cacifo próximo. O código de levantamento (`取件码`) e a morada exata da estação estão no seguimento próprio da STO, não no Peek.

Para alterar dados do destinatário, o apoio online da STO tem um tema próprio (`修改收件人信息`): pede aí (contacto abaixo) ou junto do vendedor.

## Encomenda STO sem atualizar, perdida ou devolvida

**Ainda sem dados.** O seguimento da STO nada devolve para um número ainda desconhecido ou após o limite de um ano. Se continuar vazio, confirma o número com o vendedor. Entretanto, uma encomenda sob STO Express mostra «À espera de STO Express»; dois dias após a primeira resposta vazia da STO, o Peek pede-te que confirmes o número, escolhas outra transportadora ou apagues a encomenda.

**Sem atualização.** Quando uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passa 4 dias sem novos registos, o Peek mostra a data do último registo («Sem atualizações desde …») e «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a STO Express.», com a ligação «Pede ajuda a STO Express» para o apoio online da STO, onde a falta de atualização (`快递单号没有记录更新`) é um dos temas disponíveis. Vê [porque é que o seguimento para de atualizar](guide:tracking-not-updating) e [seguir encomendas da China](guide:tracking-from-china).

**Perdida ou danificada.** Fala primeiro com o vendedor: foi ele quem contratou a STO, não tu. Segundo o acordo da STO, o remetente — ou alguém que ele nomeie — reclama em 30 dias após tomar conhecimento da perda ou dano, com prova. Sem seguro (`保价`), a STO indemniza a perda real até 500 yuans, ou até 100 yuans em documentos. Uma encomenda segurada é paga até ao valor declarado. Sem seguro nem prova de valor, a STO paga três vezes a taxa de envio. Se o atraso for culpa da STO, devolve o custo do envio. Vê [encomendas perdidas](guide:lost-parcel).

**Devolvida.** A STO pode devolver uma encomenda quando a morada não é clara, o destinatário a recusa ou duas tentativas falham sem culpa da STO; o remetente paga esse regresso. No Peek surge `Return to the sender started` e depois o percurso de volta. Vê [devolvida ao remetente](guide:returned-to-sender).

**Entregue, mas não está cá.** `签收` e `客户签收` significam que alguém assinou: tu, um coabitante, uma pessoa indicada ou uma estação ou cacifo. Verifica estações e cacifos próximos, a receção do edifício e, se aplicável, o transitário. Para reclamar usa o botão de queixa (`投诉反馈`) no apoio online da STO. Vê [entregue mas não recebida](guide:delivered-not-received).

## Contacto e apoio ao cliente da STO Express

Canais publicados pela STO:

- **Apoio ao cliente online** em `page.sto.cn`, acessível a partir de `sto.cn` em `在线咨询` (consulta online): assistente de chat com atendimento humano das 8:30 às 22:00 (hora de Pequim), com botões para enviar encomendas, consultar preços e reclamar (`投诉反馈`).
- **WeChat:** conta de serviço e miniprograma para enviar encomendas. A STO está também no Douyin. Os três constam no rodapé de `sto.cn`.

Esta página não indica telefone: o Peek só lista contactos lidos no texto das páginas oficiais da STO, e nenhuma publica um número. Sobre o pedido em si, pergunta ao vendedor.

## Seguir uma encomenda STO Express com o Peek

O formato sozinho não permite ao Peek confirmar a STO. Cola o número na mesma: o [Peek](/) acompanha mais de 4500 transportadoras.

Para um número de 15 dígitos começado por `77`, o Peek consulta logo a STO Express e a BRT, mostrando «A verificar serviços de seguimento…». Se a STO tiver a encomenda, surge «STO Express tem esta encomenda.» e o Peek regista-a lá. Caso contrário, mostra «ainda não encontrada · o Peek continua a verificar». Adiciona-a na mesma: a encomenda mostra «Provavelmente STO Express» e «À espera do primeiro registo de STO Express», e o Peek continua a perguntar à STO de 6 em 6 horas após as primeiras horas na app iPhone ou Android ou na tua conta. Numa consulta sem conta, verifica nas primeiras 6 horas, depois ao abrir o link (no máximo de 6 em 6 horas e diariamente passados dois dias) e, com notificações ligadas, diariamente durante 30 dias. Após uma semana sem nada, pede-te que confirmes o número com o remetente ou escolhas outra transportadora.

O Peek só pergunta automaticamente à STO em números começados por `77`. Num número de 12 dígitos consulta outras transportadoras com esse formato (como a BRT) e serviços de seguimento; se encontrarem a encomenda e indicarem STO Express, o Peek regista-a lá e consulta a STO na verificação seguinte.

Para ler o seguimento próprio da STO desde o início, toca em «Alterar» e escolhe STO Express em «Corresponde a este número» (12 dígitos, ou 15 começados por `77`). Para 13 dígitos ou 15 sem `77`, escolhe-a em «Todas as transportadoras» ou pesquisa `STO` ou `申通`. Numa encomenda já adicionada usa «Alterar transportadora». A pesquisa STO do Peek aceita apenas 12, 13 ou 15 dígitos: para qualquer outro tamanho indica «não emite números como este».

O Peek lê o seguimento próprio da STO, o que suporta o apoio online. Para cada registo mostra o passo (tabela acima), a cidade da instalação STO (o nome em chinês quando o Peek não consegue situá-la) e a hora, lida na hora da China e mostrada no teu fuso horário («Horas no teu fuso horário:»). Um registo repetido surge uma só vez e o peso aparece quando as balanças da STO o registam. Deixa de fora o texto integral da STO e, com ele, nomes e telefones de estafetas e estações, moradas e códigos de levantamento e quem assinou. A STO não fornece data prevista de entrega, pelo que o Peek não a mostra. O Peek segue apenas o número STO: o do transitário deve ser adicionado como outra encomenda.

Enquanto mantiveres a encomenda aberta no Peek — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a STO até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6:00 (hora de Pequim) no dia em que a STO a regista em distribuição, enquanto o rastreador da STO responder; fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora; após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Pode notificar-te para «Todos os registos», «Passos importantes» ou «Só a entrega». Não consegue contactar a STO, o transitário nem o vendedor por ti.

## Perguntas sobre o seguimento STO Express

### A STO Express é fidedigna?

Sim. Fundada em 1993, a STO Express apresenta-se como a primeira empresa privada de entregas expresso da China e pioneira do franchising no setor. Está cotada no mercado chinês de ações A e indica 75 centros de triagem, 5.075 agências independentes e mais de 98.000 lojas locais.

### O que significa 签收 no seguimento da STO?

Assinada: o Peek mostra `签收` e `客户签收` como «Entregue». Assinaste tu, um coabitante, alguém designado ou uma estação ou cacifo. `驿站代收` e `柜机代收` significam que uma estação ou cacifo guarda a encomenda para levantamento.

### Porque é que o meu número STO não mostra seguimento?

A STO ainda não leu a encomenda, o número está errado ou o envio tem mais de um ano: a STO mantém o seguimento por um ano. Confirma o número com o vendedor ou adiciona-o no Peek, que continua a verificar.

### Onde está o código de levantamento da minha encomenda STO?

No seguimento próprio da STO em `sto.cn`, com a estação ou o cacifo que guarda a encomenda. O Peek não mostra o código de levantamento (`取件码`) nem a morada; mostra `At a parcel station for collection` ou `In a parcel locker for collection` quando a encomenda está à tua espera.

### Como contactar a STO Express?

Através do apoio ao cliente online no site da STO: há atendimento humano das 8:30 às 22:00 (hora de Pequim) e botão de reclamação (`投诉反馈`). Para compras numa loja, pergunta primeiro ao vendedor.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – caixa de seguimento, conta de serviço e miniprograma WeChat, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – fundada em 1993, primeira empresa expresso privada, modelo de franchising, cotação ações A, centros de triagem, agências, lojas, cobertura municipal
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – seguimento um ano, formas de entrega, devoluções, indemnização com e sem seguro, reclamação em 30 dias, atraso
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – chat, atendimento humano 8:30–22:00, reclamações, tópicos sem atualização e alteração de dados do destinatário
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – formatos de números, tipos de registo, devoluções, hora da China, peso, código de levantamento, assinante, campos mostrados e omitidos, sem data prevista
:::
