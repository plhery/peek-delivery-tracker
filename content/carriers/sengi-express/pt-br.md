---
title: Rastreio Sengi Express: China–Itália e repasse Poste
description: Rastreie encomendas Sengi Express (grupo Poste Italiane) da China à Itália e Europa: códigos SDA/MT, alfândega, repasse Crono, atrasos e contato.
slug: sengi-express-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Sengi Express** (*Guangzhou Sengi Logistics Co., Ltd.* / *Seng Yi (Shenzhen) Global Supply Chain Management Co., Ltd.*, em chinês *广州森吉物流有限公司*) é uma operadora de logística internacional controlada majoritariamente pelos **Poste Italiane** ([portal.sengiexpress.com/about/](https://portal.sengiexpress.com/about/)). Especializada em injeção direta de e-commerce da China para a Itália e a Europa, a Sengi gerencia consolidação na origem chinesa, frete aéreo e marítimo, desembaraço aduaneiro B2C/B2B na Europa em portões como Milão Malpensa (**MXP**), Amsterdã (**AMS**), Liège (**LGG**) e Paris (**CDG**), transporte rodoviário europeu via Sennder (do grupo Poste Italiane) e entrega final em domicílio ou armário na Itália pela rede Crono dos [Poste Italiane](carrier:poste-italiane). Rastreie em [portal.sengiexpress.com](https://portal.sengiexpress.com/home) ou cole seu código na caixa acima.

## Códigos de rastreio Sengi Express (formatos SDA `...C...F` e `MT`)

A Sengi Express frequentemente emite já na China um conhecimento **Poste Italiane / SDA** de 13 caracteres, de modo que o mesmo código acompanha o pacote desde o armazém chinês da Sengi até a porta na Itália, além de códigos internacionais de 16 caracteres terminados em `MT`:

| Formato (inventado) | O que é |
| --- | --- |
| `482C73918462F` | Código SDA / Poste Italiane de 13 caracteres (`3 dígitos` + `C` + `8 dígitos` + `F`) acompanhado pela Sengi Express antes de os Poste Italiane registrarem o pacote na Itália. |
| `SENGI482917360MT` | Código internacional Sengi de 16 caracteres (`5 letras` + `9 dígitos` + `MT`). |

A Sengi atribui o número SDA a encomendas para a Itália antes de a SDA conhecer o código, por isso o rastreador dos Poste Italiane pode não encontrá-lo no início; o rastreador da Sengi acompanha do armazém chinês até o destinatário na Itália. Quando selecionada manualmente, a Sengi também aceita outros códigos alfanuméricos de parceiros entre 8 e 40 caracteres. Veja [formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio Sengi Express

A Sengi Express publica descrições internacionais em inglês (e em chinês) acompanhadas de códigos opcionais de evento e carimbos de data e hora:

| A Sengi Express exibe | O que significa para você | O que o Peek mostra |
| --- | --- | --- |
| `Order information received. We're expecting your parcel to arrive with us.` (código `10`) | O vendedor enviou os dados eletrônicos antes da entrada física no armazém chinês da Sengi. | "Etiqueta criada" |
| `Arrived at Destinated Airport`, `Terminal Notification of Arrival` | O voo internacional pousou no aeroporto europeu de entrada (como MXP, AMS, LGG ou CDG). | "Em trânsito" |
| `Arrived at customs clearance warehouse` | A encomenda deu entrada no armazém alfandegado europeu. | "Na alfândega" |
| `Customs clearance inspection, Reason: supposed as undervaluation, waiting for customs check(VM)` | Selecionada pela alfândega europeia para conferência de valor declarado ou conteúdo (`VM`). | "Na alfândega" |
| `Ready for transit to last mile operator` | Liberada pela alfândega e na fila de repasse aos [Poste Italiane](carrier:poste-italiane) ou transportadora local. | "Em trânsito" |

Qualquer leitura com outro texto recebe o estágio correspondente à sua redação. A Sengi informa que destinatários também podem acompanhar nos [Poste Italiane](carrier:poste-italiane) usando o mesmo código `...C...F` ou o novo número quando a Sengi reetiqueta o pacote.

## Injeção direta, portões aduaneiros europeus e entrega Poste Italiane

Segundo [Sengi Express About](https://portal.sengiexpress.com/about/) e [Sengi Express Products](https://portal.sengiexpress.com/product/):

- **Portões aéreos na Europa:** a Sengi opera desembaraço aduaneiro direto B2C e B2B em Milão Malpensa (`MXP`), Amsterdã (`AMS`), Liège (`LGG`) e Paris Charles de Gaulle (`CDG`).
- **Serviços finais Poste Italiane:** na Itália, a Sengi injeta os pacotes nos serviços **Crono** (encomenda padrão), **Crono Express**, **Extralarge** (volumes grandes / última milha FBA), **Crono ADP** (armários de retirada) e **Crono Reverse** (devoluções).
- **Fiscalização de valor (`VM`) e taxas da UE:** remessas da China para a União Europeia seguem as regras aduaneiras europeias, IVA de importação (via IOSS ou na entrega) e tarifas/taxas de manuseio da UE sobre pedidos de fora do bloco. Se aparecer `supposed as undervaluation, waiting for customs check(VM)`, a alfândega está verificando o valor declarado junto ao importador/despachante — aguarde a liberação ou avise o vendedor. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Sengi Express parada, sem atualização ou sumida

Como o contrato de transporte com a Sengi Express pertence aos marketplaces e vendedores chineses, **procure sempre primeiro seu vendedor ou marketplace** para reembolso, reenvio ou documentos alfandegários.

- **Pausa antes dos registros italianos:** é comum o pacote ficar vários dias sem novidades entre `Order information received`, `Arrived at Destinated Airport` e `Ready for transit to last mile operator` enquanto lotes aéreos passam pela alfândega europeia e seguem de caminhão até a Itália. Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).
- **Acompanhamento nos Poste Italiane após o repasse:** assim que aparecer `Ready for transit to last mile operator` em um envio `PI` para a Itália, acompanhar o mesmo número `...C...F` (ou o novo número reetiquetado mostrado pelo Peek) nos [Poste Italiane](carrier:poste-italiane) mostra triagem italiana, tentativas de entrega e detalhes de Punto Poste ou armário.
- **Perdida, danificada ou marcada como entregue:** confira primeiro com vizinhos e no Punto Poste ou agência local dos Poste Italiane, depois abra disputa no marketplace antes do fim da proteção ao comprador. Veja [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos Sengi Express

Canais oficiais publicados em [Sengi Express About](https://portal.sengiexpress.com/about/) e [Sengi Express Contact](https://portal.sengiexpress.com/contact/):

- **E-mail:** `service@sengiexpress.com`.
- **Formulário on-line:** [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).
- **Operadora:** *Guangzhou Sengi Logistics Co., Ltd.* (*广州森吉物流有限公司*, ICP `粤ICP备2021101066号`), controlada majoritariamente pelos Poste Italiane.

> Desconfie de SMS falsos alegando que há taxa dos Poste Italiane ou da alfândega a pagar por link desconhecido. Confirme qualquer cobrança legítima diretamente na página do pedido no marketplace ou no site oficial dos Poste Italiane. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Acompanhe sua encomenda Sengi Express no Peek

Ao inserir um código `...C...F` de 13 caracteres (`482C73918462F`, inventado), o [Peek](/) consulta primeiro os [Poste Italiane](carrier:poste-italiane), depois AliExpress e Sengi Express (Sengi antes do AliExpress na Itália; na China, AliExpress primeiro e depois Sengi) e confirma quando a Sengi tem o pacote. Para códigos terminados em `MT` (`SENGI482917360MT`, inventado), o Peek consulta o [MaltaPost](carrier:maltapost), cujos números também terminam em `MT`: para esse código ou outro conhecimento de parceiro, toque em "Alterar" ao adicionar ou "Alterar transportadora" na tela do pacote e escolha **Sengi Express** (buscar "Sengi" também encontra).

O Peek lê o rastreador da Sengi e mostra a etapa ("Etiqueta criada", "Em trânsito", "Na alfândega" ou outra conforme a redação), o texto em inglês da Sengi (ou em chinês quando não há inglês) e o local. Os códigos numéricos de evento determinam a etapa e não são exibidos nos aplicativos do Peek. A Sengi informa deslocamento UTC em seus horários; leituras sem fuso recebem o fuso do local (Itália para `MXP`, Pequim para Guangzhou) ou da leitura vizinha, e tudo aparece no fuso do seu dispositivo. Na rota Poste Italiane da Sengi, o Peek verifica quando os Poste Italiane recebem o pacote, exibindo "Entrega por Poste Italiane" e mostrando o novo código se houver reetiquetagem.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Sengi exibir saída para entrega (o que até hoje não costuma ocorrer), a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a Sengi não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando o último registro tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca a data da leitura (por exemplo, "Sem atualização de rastreamento desde seg 12 out") com a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Sengi Express." e link para a página de contato da Sengi. Após três semanas sem novidade, orienta procurar o vendedor enquanto continua consultando.

Você pode escolher alertas de "Cada atualização", "Etapas importantes" ou "Somente entrega". O Peek apenas acompanha o rastreamento e não libera cargas na alfândega, não altera endereços nem contata a Sengi Express ou os Poste Italiane por você.

## Perguntas sobre o rastreio Sengi Express

### O que é a Sengi Express?

A Sengi Express (*Guangzhou Sengi Logistics Co., Ltd.*) é uma operadora logística internacional de e-commerce da China para a Itália e Europa controlada pelos **Poste Italiane**, responsável por consolidação na origem, desembaraço aduaneiro na Europa (MXP, AMS, LGG, CDG) e injeção final na rede Crono dos Poste Italiane.

### Por que meu código `...C...F` dos Poste Italiane aparece primeiro na Sengi Express?

A Sengi atribui o código SDA / [Poste Italiane](carrier:poste-italiane) (`482C73918462F`, inventado) antes de a SDA conhecer o número. Assim, o rastreador da Sengi mostra os registros de voo e alfândega enquanto o site dos Poste Italiane ainda não encontra o código.

### O que significa `supposed as undervaluation, waiting for customs check(VM)`?

A alfândega europeia selecionou a encomenda para inspeção de valor declarado (`VM`). O despachante ou o vendedor cuida da documentação de liberação; fale com seu vendedor se permanecer nesse status.

### Como entrar em contato com o atendimento da Sengi Express?

Fale primeiro com sua loja virtual ou marketplace para reembolsos ou documentos aduaneiros. A Sengi Express disponibiliza `service@sengiexpress.com` e formulário on-line em [portal.sengiexpress.com/contact/](https://portal.sengiexpress.com/contact/).

:::sources
- [Sengi Express: Tracking Portal](https://portal.sengiexpress.com/home) – rastreador público de conhecimentos e dados da *Guangzhou Sengi Logistics Co., Ltd.*
- [Sengi Express: About Us](https://portal.sengiexpress.com/about/) – controle majoritário pelos Poste Italiane, serviços internacionais e `service@sengiexpress.com`
- [Sengi Express: Products & Services](https://portal.sengiexpress.com/product/) – portões aduaneiros MXP/AMS/LGG/CDG, Poste Italiane Crono / Crono Express / Extralarge / Crono ADP / Crono Reverse e transporte rodoviário Sennder na UE
- [Sengi Express: Contact](https://portal.sengiexpress.com/contact/) – página oficial de contato (URL de ajuda vinculada pelo Peek)
- [Universal Parcel Scraper: sengi-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sengi-express) – regras de detecção `...C...F` e `...MT`, analisador `api-v2/tracking/web`, mapeamento de status, tratamento de fuso UTC e repasse `PI` / `relabelTrackingNumber` para Poste Italiane
:::
