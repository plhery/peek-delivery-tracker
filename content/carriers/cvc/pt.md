---
title: Seguimento CVC (Си Ви Си): seguir encomenda na Bulgária
description: Acompanha uma encomenda CVC (Си Ви Си, e-CVC) na Bulgária com o Peek: adicionar o número, escolher CVC, significado dos estados e o que fazer se parar.
slug: seguimento-cvc
published: 2026-11-23
updated: 2026-11-23
---

A CVC é uma transportadora da Bulgária. O catálogo do Peek também a conhece por Си Ви Си, Си Ви Си ЕООД e e-CVC. Para seguir uma encomenda CVC, cola uma ligação para o seguimento da CVC na caixa acima, ou cola o número e escolhe CVC como transportadora: o Peek não consegue identificar um número CVC apenas pela forma. O número vem da loja ou da pessoa que enviou a encomenda. O seguimento próprio da CVC está em my.e-cvc.bg/track, em búlgaro: pede o número da guia de transporte e tem um único botão, «Провери» (Verificar).

Os sites da CVC pouco mais dizem: my.e-cvc.bg mostra apenas esse seguimento e uma página de início de sessão, sem páginas públicas com regras de entrega, preços ou contactos. Esta página explica, por isso, o que o seguimento da CVC pede e como o Peek acompanha uma encomenda CVC, sem incluir regras de entrega, preços nem contactos que a CVC não publica.

## Número de seguimento CVC: que aspeto tem

O seguimento da CVC pede um número de guia de transporte (*товарителница* em búlgaro). A caixa aceita apenas dígitos: remove letras e espaços à medida que escreves e aceita no máximo 20 caracteres. Um número CVC é assim feito de até 20 dígitos, mas o catálogo do Peek não atribui qualquer formato próprio à CVC: o Peek não o reconhece automaticamente como CVC e a CVC nunca aparece em «Corresponde a este número» ao adicionar. Esta página não dá nenhum exemplo inventado porque o seguimento da CVC não mostra como é um número completo.

| O que colas | O que o Peek faz |
| --- | --- |
| Apenas o número CVC | Não indica CVC. Pode mostrar outras transportadoras cujo formato coincida, ou nenhuma. Escolhe CVC manualmente, conforme explicado em «Seguir uma encomenda CVC com o Peek» abaixo. |
| Uma ligação para o seguimento da CVC: `my.e-cvc.bg/track?wb=` seguido do número | O Peek lê o número na ligação e identifica logo a CVC («Transportadora detetada»). |
| Um número que a loja indica ser de outra transportadora | Acompanha-o nessa transportadora: o e-mail da loja é a melhor pista sobre quem entrega. |

**Onde encontrá-lo.** No e-mail de envio da loja ou na página do pedido, por vezes referido como número de guia (*waybill*). Se veio de um particular, pergunta-lhe. Sem número? Vê [como encontrar um número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats) para outras transportadoras.

## O que significam os estados de seguimento CVC no Peek

O Peek lê diretamente a página de seguimento da CVC em my.e-cvc.bg/track e apresenta cada linha nas palavras búlgaras da CVC, organizada sob uma etapa do Peek em português. As principais mensagens búlgaras reconhecidas pelo Peek:

| A CVC diz | O Peek mostra | O que significa para ti |
| --- | --- | --- |
| `Приета за събиране` | Etiqueta criada | O envio foi registado para recolha; a CVC ainda não recolheu a encomenda. |
| `Отложено събиране за …` | Etiqueta criada | A recolha foi adiada para a data indicada. |
| `Взета от куриер …` | Aceite pela transportadora | Um estafeta da CVC já recolheu a encomenda. |
| `Приета в хъб …`, `Транспорт към хъб …` | Em trânsito | Chegou a um hub da CVC ou segue a caminho de um hub. |
| `Приета за разнос` | Em distribuição | A encomenda saiu hoje para entrega. |
| `Доставена`, `Доставена на …` | Entregue | A encomenda foi entregue. |

Quando o seguimento da CVC não encontra o número, responde `Пратката не е открита!` (envio não encontrado). Se o seguimento próprio da CVC não responder, o Peek pode também consultar serviços de seguimento como ParcelsApp, Ship24 e 17TRACK, apresentando o histórico do primeiro que tiver a encomenda nas etapas habituais do Peek («Pronta para levantamento», «Tentativa de entrega», «Devolvida ao remetente», «Problema reportado»).

Antes de existir qualquer registo, o Peek mostra «A procurar atualizações» enquanto consulta e depois «À espera de CVC». Se duas verificações seguidas falharem, mantém «À espera de CVC» e a página indica: «Ainda não foi possível contactar CVC. O Peek continua a tentar.» Mais em [estados de seguimento explicados](guide:tracking-statuses).

## Entregas CVC na Bulgária

Os sites da CVC não publicam prazos de entrega, tentativas, agências, regras de envio à cobrança nem prazos de recolha: my.e-cvc.bg apresenta apenas o seguimento e a página de acesso. Pergunta à loja ou ao remetente como e quando a CVC entrega, pois foram eles quem contratou o serviço. Se uma entrega falhar ou ficar à espera nalgum ponto, vê [entrega falhada](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Encomenda CVC sem atualizações, perdida ou devolvida

**Ainda nada.** A CVC pode ainda não conhecer o número. O Peek mostra então «À espera de CVC» e continua a verificar. Se continuarem sem existir registos dois dias após adicionares a encomenda, o Peek assinala: «À espera da primeira atualização da transportadora». Confirma o número no e-mail da loja. Se nada aparecer após alguns dias, abre o seguimento da CVC com «Abrir o site de CVC» na página da encomenda e pergunta à loja se foi realmente enviada.

**Sem novidades há dias.** Após quatro dias sem novo registo, uma encomenda ainda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» mostra «Sem atualizações há quatro dias». Vê [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não chegou nada.** Verifica junto à porta e com os vizinhos e depois fala com a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Avisa primeiro a loja: foi ela que marcou a entrega e pode tratar com a CVC ou enviar uma nova encomenda. Vê [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Devolvida.** «Devolvida ao remetente» significa que voltou à loja ou ao remetente. Pede reembolso ou novo envio. Vê [devolvida ao remetente](guide:returned-to-sender).

## Contacto e apoio ao cliente da CVC

Os sites da CVC não indicam telefone, e-mail nem balcão: my.e-cvc.bg mostra apenas o seguimento e o início de sessão. O Peek só indica contactos lidos em páginas oficiais da transportadora, pelo que esta página não lista nenhum. Fala primeiro com a loja ou o remetente, com os números de pedido e seguimento à mão: foram eles quem contratou a entrega com a CVC.

## Seguir uma encomenda CVC com o Peek

O [Peek](/) acompanha mais de 4 500 transportadoras, incluindo a CVC, mas não reconhece uma encomenda CVC apenas pelo número. Uma ligação para o seguimento da CVC identifica-a logo; só com o número, escolhe a transportadora manualmente:

1. Cola o número na caixa acima.
2. Toca em «Alterar» junto à transportadora e pesquisa por CVC. «e-CVC» ou «Си Ви Си» também encontram (com «Também conhecida como e-CVC» ou «Também conhecida como Си Ви Си» por baixo), tal como «Bulgaria».
3. Escolhe CVC: a linha passa a dizer «Escolhida por ti». Inicia o seguimento.

Numa encomenda já adicionada, usa «Alterar transportadora». Para a CVC, o Peek aceita apenas de 1 a 20 dígitos; fora disso indica que a CVC «não emite números como este». Se o Peek listar outras transportadoras em «Corresponde a este número», elas apenas coincidem no formato. Antes de escolheres, a linha pode mostrar «ainda não encontrada · o Peek continua a verificar», algo normal num número novo.

Se deixares a escolha ao Peek, ele consulta serviços de seguimento: se mostrarem a encomenda em movimento e indicarem CVC como transportadora, o Peek atribui-a automaticamente à CVC.

**O que vês.** O estado e cada linha do histórico com data e hora lidas na hora local da Bulgária. A CVC não indica localidade do registo, dia previsto de entrega, ponto de recolha nem peso. «Abrir o site de CVC» abre o número diretamente no seguimento búlgaro da CVC.

**Outra transportadora.** Nos primeiros 30 dias da encomenda, o Peek pergunta também às transportadoras cujo formato coincida com o número. Se uma delas mostrar a encomenda em curso, o Peek transfere-a e mostra «Alterado automaticamente de CVC».

**Frequência.** Enquanto o seguimento da CVC responder, o Peek verifica até de 10 em 10 minutos, e de 2 em 2 a partir das 6h locais no dia em que a CVC regista a encomenda em distribuição (`Приета за разнос`). Após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. As notificações abrangem «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega».

O Peek não pode contactar a CVC, a loja ou o remetente por ti, nem alterar a entrega ou abrir reclamação.

## Perguntas sobre o seguimento CVC

### Como sigo uma encomenda CVC?

Cola uma ligação para o seguimento da CVC na caixa acima e o Peek seleciona CVC sozinho. Apenas com o número, toca em «Alterar» e escolhe CVC: o Peek não reconhece a forma automaticamente. Numa encomenda já adicionada, usa «Alterar transportadora». O seguimento oficial fica em my.e-cvc.bg/track: escreve o número da guia e carrega em «Провери».

### Que aspeto tem um número de seguimento CVC?

É um número de guia de transporte (*товарителница*) composto apenas por dígitos: o seguimento da CVC remove letras e espaços e aceita no máximo 20 dígitos. O catálogo do Peek não tem um formato exclusivo para a CVC, pelo que deves escolhê-la manualmente.

### A e-CVC é o mesmo que a CVC?

Sim. O seguimento da CVC em my.e-cvc.bg chama-se e-CVC.bg e exibe o logótipo da CVC, e o catálogo do Peek lista e-CVC, Си Ви Си e Си Ви Си ЕООД como outros nomes da CVC. Pesquisar qualquer um deles na lista de transportadoras do Peek encontra a CVC.

### Porque diz a minha encomenda CVC «À espera de CVC»?

Em peektracker.com, uma encomenda registada na CVC mostra esta mensagem enquanto o seguimento da CVC (ou um serviço de seguimento) ainda não tiver registos para o número ou não puder ser contactado: a página mostra então «Ainda não foi possível contactar CVC. O Peek continua a tentar.» Confirma o número com a loja e verifica-o com «Abrir o site de CVC».

### Qual é o número de telefone da CVC?

Esta página não indica nenhum porque os sites da CVC não publicam telefone. Pergunta à loja que enviou a encomenda: foi ela quem contratou a entrega com a CVC.

:::sources
- [CVC: seguimento e-CVC.bg](https://my.e-cvc.bg/track) – seguimento em búlgaro, «Потърсете товарителница», apenas dígitos, máximo 20 caracteres, «Провери», número após `wb=`, nome e-CVC.bg, logótipo CVC
- [CVC: início de sessão e-CVC.bg](https://my.e-cvc.bg/) – apenas página de acesso, sem páginas de entregas, preços nem contactos
- [Universal Parcel Scraper: CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – nomes e alias, Bulgária, endereço do seguimento, regra de ligação, sem formato de número, leitura direta de my.e-cvc.bg/track e estados em búlgaro
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – serviços de seguimento de apoio, correspondência do nome da transportadora com a CVC, ligação para o site da transportadora
:::
