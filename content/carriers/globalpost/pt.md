---
title: Seguimento GlobalPost: números GP, estados e entrega
description: Segue uma encomenda GlobalPost: formato dos códigos GP e SDC, significado dos estados em inglês, serviços, alfândega em Portugal e uso do Peek.
slug: seguimento-globalpost
published: 2026-11-21
updated: 2026-11-21
---

A GlobalPost, integrada no grupo Auctane, é um serviço de expedição internacional contratado por vendedores online através de plataformas como a ShipStation e a Stamps.com (a GlobalPost envia para mais de 220 países e afirma ter entregue mais de 60 milhões de remessas). A encomenda segue primeiro para um centro de expedição da GlobalPost, onde recebe a etiqueta internacional e a documentação aduaneira, sendo depois encaminhada por parceiros postais e comerciais até aos correios ou transportadora que entrega no destino. O vendedor indica-te o número, que começa habitualmente por `GP`: cola-o na caixa acima. O localizador oficial está em `goglobalpost.com/tracking`; no catálogo do Peek também encontras a GlobalPost pesquisando `Go GlobalPost`, `GoGlobalPost`, `Global Post`, `Go Global Post` ou `GlobalPost UK`.

## Número de seguimento da GlobalPost: como é

O Peek reconhece os números atuais da GlobalPost pelo formato: `GP`, quatro letras do serviço, dez algarismos e `US` ou `GB` conforme o país de origem. Com letras e algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `GPECON4829105736US` | `GP`, quatro letras, dez algarismos e `US`: uma encomenda GlobalPost expedida dos Estados Unidos. |
| `GPSTND4829105736GB` | O mesmo formato terminado em `GB`: expedida do Reino Unido. |
| `SDC482910573649182` | `SDC` e 15 algarismos: uma etiqueta mais antiga da GlobalPost. Só pelo número o Peek não identifica a transportadora: escolhe a GlobalPost em «Alterar». |
| `GPECON482910573US` | Apenas nove algarismos: o Peek não reconhece nenhuma transportadora. Confirma se falta um dígito. |
| `LZ482910576US` | Um código postal norte-americano, não da GlobalPost: o Peek indica a [USPS](carrier:usps). Se selecionares a GlobalPost manualmente, o Peek avisa que «não emite números como este». |

O Peek ignora espaços, traços e minúsculas.

**Onde encontrar o número.** No e-mail de envio do vendedor ou na página da encomenda. Se não o encontrares, consulta [como encontrar o número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

**Um segundo número.** Em `Final Mile Tracking By:`, o localizador da GlobalPost pode indicar o código próprio da empresa que entrega no destino e também abre o envio por esse número (enquanto o Peek consulta a GlobalPost apenas com códigos `GP` ou `SDC`).

## Estados de seguimento da GlobalPost explicados

O localizador da GlobalPost lista as leituras da mais recente para a mais antiga sob cabeçalhos diários, com hora, descrição em inglês e quase sempre local, apresentando nas suas próprias palavras os eventos dos parceiros. Pela ordem habitual:

| A GlobalPost diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Label Created` | O vendedor criou a etiqueta; nada avançou ainda. | «Etiqueta criada» |
| `Data Received` | A GlobalPost recebeu os dados eletrónicos do vendedor, ainda não o pacote. | «Etiqueta criada» |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Um parceiro leu a encomenda: a USPS a caminho da GlobalPost ou o operador postal no destino. | «Em trânsito» |
| `Arrived at GlobalPost Facility` | Um centro de expedição da GlobalPost recebeu o volume (leituras da USPS a caminho podem surgir antes). | «Aceite pela transportadora» |
| `Departed GlobalPost Facility` | Saiu desse centro da GlobalPost. | «Em trânsito» |
| `Departed from Origin Country` | Saiu dos Estados Unidos ou do Reino Unido. | «Em trânsito» |
| `In Transit`, `In-Transit` | Em trânsito. | «Em trânsito» |
| `Arrived in Destination Country` | Chegou ao país de destino. | «Em trânsito» |
| `In Customs` | Na alfândega de importação. | «Na alfândega» |
| `Cleared Customs` | Desalfandegamento concluído; segue viagem. | «Em trânsito» |
| `Out for Delivery` | Em distribuição hoje. | «Em distribuição» |
| `Attempted Delivery` | Tentativa de entrega sem sucesso. | «Tentativa de entrega» |
| `Exception, No Action Required` | Imprevisto na entrega no país de chegada que não exige ação da tua parte. | «Problema reportado» |
| `Delayed, No Action Required` | Atraso registado sem que precises de fazer nada. | «Problema reportado» |
| `Delivered` | Entregue (ou devolvida ao remetente: vê abaixo). | «Entregue» |

Uma linha pode terminar com a nota da GlobalPost de que as atualizações podem demorar: o Peek classifica o estado anterior mantendo o texto completo, e um registo listado depois de o pacote avançar não faz recuar o estado. Outras frases são lidas pelas regras gerais do Peek.

**Devoluções.** O localizador da GlobalPost também termina uma devolução com `Delivered`, mostrando a localidade do destinatário no cabeçalho embora a leitura indique a cidade do remetente. Quando a localidade da leitura coincide com a do remetente noutro país e noutra cidade que não os do destinatário, o Peek mostra «Devolvida ao remetente».

## Quem entrega as encomendas da GlobalPost em Portugal

A GlobalPost entrega as remessas *Economy* e *Standard* a um operador postal (como a USPS nos EUA) para a viagem internacional. As suas páginas não listam parceiros de entrega por país, mas o localizador pode indicar o nome e o número da empresa final.

**O detalhe do seguimento depende do serviço** contratado pelo vendedor:

| Serviço | Seguimento | Direitos e impostos | Prazo indicado ao vendedor |
| --- | --- | --- | --- |
| `Economy` | até entrar no país e sair da alfândega | pagas tu (`DDU`) | `10 – 14 business days` |
| `Standard` | até à tua porta | pagas tu (`DDU`) | `6 – 10 business days` |
| `Plus`, `Plus – Duties & Taxes Included` | até à tua porta | paga o vendedor (`DDP`) | `5 – 8 business days` |
| `Flat Rate (formerly Go)` | até à tua porta | incluídos no preço do vendedor | `6 – 10 business days` |

A partir dos Estados Unidos, o *GlobalPost Plus – Duties & Taxes Included* abrange **Portugal** (além do Canadá e de outros países europeus), enquanto o *Flat Rate (formerly Go)* serve o Canadá, Austrália, Nova Zelândia, Hong Kong e México. As tentativas de entrega e o levantamento seguem as regras da empresa final: consulta os [CTT](carrier:ctt), [entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).

**Alfândega em Portugal e na União Europeia.** Uma encomenda vinda dos Estados Unidos ou da Grã-Bretanha passa na alfândega ao entrar na União Europeia (já a **Irlanda do Norte** é tratada como território da UE para efeitos aduaneiros e de IVA sobre bens, segundo a Comissão Europeia). Em *Economy* e *Standard* (*Delivered Duties Unpaid*), os direitos e impostos ficam a cargo do comprador. Na **União Europeia**, desde 1 de julho de 2026 os bens comprados online fora da UE em remessas com valor intrínseco até 150 € pagam um direito aduaneiro da UE de 3 € por artigo (*per item*), e desde 1 de novembro de 2026 soma-se uma taxa de manuseamento da União (*Union handling fee*) de 2 € por artigo seja qual for o valor (um artigo reúne mercadorias da mesma encomenda com igual classificação pautal, descrição e, quando exigida, origem; acima de 150 € aplicam-se os direitos normais), além do IVA de importação em **Portugal** (incluindo os **Açores** e a **Madeira**, que segundo o âmbito territorial publicado pela Comissão Europeia fazem parte tanto do território aduaneiro como da área de IVA da UE), salvo se o IVA já tiver sido cobrado na compra através do IOSS. As páginas da GlobalPost não detalham como é cobrada a taxa de 2 €: pergunta ao vendedor se estava incluída e vê [encomenda retida na alfândega](guide:customs).

## Encomenda GlobalPost parada, perdida ou devolvida

**«À espera de GlobalPost».** Enquanto o localizador redirecionar indicando que não encontra detalhes para o número (`status=not_found`), o Peek mostra «À espera de GlobalPost» e continua a verificar. Se ao fim de dois dias a GlobalPost continuar sem conhecer o código e nenhum serviço de seguimento o encontrar, o Peek avisa: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» Se ficar apenas em `Label Created` durante vários dias, pergunta ao vendedor se já expediu o pacote.

**Vários dias sem atualização.** A GlobalPost refere que atualizações internacionais podem demorar (meteorologia, feriados, greves, alfândega) e que no serviço *Economy* o seguimento termina após a alfândega no destino. Ao fim de quatro dias sem novas leituras em trânsito, o Peek assinala o dia da última leitura («Sem atualizações desde seg 12 out»), lembrando as pausas habituais em voos longos, alfândega ou fim de semana; passadas três semanas sem nada de novo, sugere contactar o vendedor. Não há página de ajuda da GlobalPost associada; assim que o Peek acompanha a remessa na empresa final e conhece a página de apoio desta, mostra essa ligação. Lê [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Não chegou ao fim de 30 dias.** Se a encomenda não chegar 30 dias após o envio, a GlobalPost pede que contactes primeiro o vendedor: cabe-lhe averiguar e reclamar junto da GlobalPost no prazo de `90 days` após a expedição, demorando a análise até `3 weeks`. Consulta [encomenda perdida](guide:lost-parcel).

**Dada como entregue mas não recebida, ou não entregável / devolvida.** Se constar entregue sem ter chegado, fala primeiro com a empresa de entrega indicada e depois com o vendedor ([entregue mas não recebido](guide:delivered-not-received)). Em caso de morada incorreta ou incompleta, direitos ou impostos por pagar, avisos aduaneiros falhados ou artigos restritos, o operador postal de destino trata do pacote (normalmente devolve-o ao remetente ou guarda-o em estação) sem dar à GlobalPost data nem novo número de devolução; os termos da GlobalPost excluem encomendas não entregáveis (*undeliverable*) de indemnização, pelo que deves tratar do reembolso com a loja ([devolvida ao remetente](guide:returned-to-sender)).

## Apoio ao cliente e contactos da GlobalPost

Na página *Contact Support*, a GlobalPost disponibiliza estes contactos:

- **E-mail**: `info@goglobalpost.com`.
- **Telefone**: `(888) 899-1255`, de segunda a sexta-feira das `6am` às `6pm` hora do Pacífico (*Pacific Time*).
- **Formulário de contacto**: em `www.goglobalpost.com/contact-support/`, escolhe `Recipient` e introduz o número de seguimento (a GlobalPost indica que a equipa de suporte vê a mesma informação apresentada na página pública de seguimento).

Para uma encomenda em falta, a GlobalPost encaminha primeiro para o vendedor que reservou o envio. Esclarece também que um pacote vindo da China enviado pela [China Post](carrier:china-post) não é um envio GlobalPost: fala nesse caso com o remetente.

**Cuidado com SMS fraudulentos.** O domínio oficial da GlobalPost é `www.goglobalpost.com` (`goglobalpost.com`). Antes de seguires ligações recebidas por SMS ou e-mail, consulta [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da GlobalPost com o Peek

O [Peek](/) identifica de imediato um número `GP` («Transportadora detetada») e consulta o localizador da GlobalPost sem conta nem código postal (o site guarda em cache durante alguns minutos cada página encontrada). Uma ligação `goglobalpost.com/track-detail/?t=` ou `/track/?t=` também funciona; para um código `SDC` antigo, escolhe a GlobalPost em «Alterar». Se tiveres apenas o número da transportadora de entrega, cola-o na mesma: o Peek acompanha mais de 3 500 transportadoras.

O Peek mostra o estado, cada leitura com o seu local (cortando códigos postais), o país de destino e a empresa de entrega com o respetivo número quando a GlobalPost os indica. Deixa de fora a barra de progresso, as localidades de origem e destino do cabeçalho (lidas só para identificar devoluções) e os códigos postais; a GlobalPost não dá data prevista de entrega, peso nem comprovativo de entrega. «Abrir o site de GlobalPost» abre o teu código em `goglobalpost.com`.

As horas da GlobalPost não trazem fuso horário: o Peek lê cada leitura pelo relógio do seu local, ou do troço em que a encomenda ia, e mostra-a no fuso horário do teu dispositivo; em `peektracker.com` bem como nas aplicações iPhone e Android, o histórico termina com «Horas no teu fuso horário: …».

Quando a GlobalPost nomeia uma transportadora reconhecida pelo Peek por esse nome, o cartão apresenta «Entrega: …» e lista o número dela depois do código `GP`, ou «Anunciada a …, ainda não recebida» enquanto essa empresa tiver apenas o anúncio sem leituras próprias. Com segundo número mas sem empresa reconhecida pelo nome, perante um código postal válido o Peek consulta o operador postal do país de destino (os [CTT](carrier:ctt) em Portugal) ou a única transportadora indicada pelo formato do número. Mal essa empresa apresente uma leitura pelo menos tão recente como a última da GlobalPost, o Peek passa a segui-la lá mantendo o histórico da GlobalPost.

Enquanto mantiveres a encomenda aberta ou, no dia da entrega, tiveres notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos (a cada 5 minutos quando precisa de abrir o localizador da GlobalPost num navegador) a partir das 6:00 locais no dia em que passa a «Em distribuição» enquanto o localizador da GlobalPost responder (após transição para a transportadora final, o Peek acompanha o ritmo desta; apenas por serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ativas) —; nos outros casos, e após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes ativar alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a GlobalPost nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da GlobalPost

### Porque é que o meu seguimento da GlobalPost parou depois da alfândega?

Com o *GlobalPost Economy*, o seguimento termina assim que a encomenda chega ao país de destino e passa na alfândega, e a GlobalPost refere que alguns envios têm seguimento limitado a partir daí. O pacote continua a caminho e pode chegar sem novas linhas; se o localizador indicar um número em `Final Mile Tracking By:`, o Peek consulta essa transportadora e tu também o podes fazer.

### Quem entrega as encomendas da GlobalPost?

Os correios ou uma transportadora no país de chegada, que o localizador da GlobalPost pode identificar com o respetivo número. Quando consta um código postal internacional sem parceiro reconhecido pelo nome, o Peek consulta o operador postal do destino (os CTT em Portugal).

### Quanto tempo demora a GlobalPost a entregar?

As FAQ referem `5-6 days` para países próximos e até `20 days` para destinos mais distantes, e ao vendedor a GlobalPost indica `10 – 14 business days` em *Economy*, `6 – 10 business days` em *Standard* e *Flat Rate* e `5 – 8 business days` em *Plus*, como estimativa desde o processamento no centro de expedição.

### A GlobalPost é fidedigna?

Sim. A GlobalPost faz parte da Auctane, é vendida em plataformas de expedição como a ShipStation e a Stamps.com, envia para mais de 220 países e afirma ter entregue mais de 60 milhões de remessas.

### Como posso contactar a GlobalPost?

Envia e-mail para `info@goglobalpost.com`, liga para `(888) 899-1255` nos dias úteis das `6am` às `6pm` hora do Pacífico ou preenche o formulário em `goglobalpost.com/contact-support/` selecionando `Recipient`. Se a encomenda não tiver chegado 30 dias após o envio, a GlobalPost pede que fales primeiro com o vendedor.

:::sources
- [GlobalPost: FAQs](https://www.goglobalpost.com/faqs/) – Auctane, funcionamento, países de Plus – Duties & Taxes Included e Flat Rate, seguimento Economy até à alfândega, prazos, devoluções e reclamações em 90 dias / 3 semanas
- [GlobalPost: Standard](https://www.goglobalpost.com/standard/) – comparação entre Economy, Standard, Plus e Flat Rate: seguimento, encargos DDU/DDP e dias úteis
- [GlobalPost: Contact Support](https://www.goglobalpost.com/contact-support/) – e-mail, telefone, horário do Pacífico, opção Recipient no formulário e nota sobre a China Post
- [GlobalPost: Home](https://www.goglobalpost.com/) – mais de 220 países e mais de 60 milhões de envios entregues
- [GlobalPost: Track your package](https://www.goglobalpost.com/tracking/) – localizador oficial e aviso de número não localizado
- [GlobalPost: Terms and Conditions](https://www.goglobalpost.com/conditions/) – centros de expedição, prazos estimativos e exclusão de encomendas não entregáveis
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Orientações sobre o direito de 3 € e a taxa da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – valor fixo de 2 € independentemente do preço, definição de artigo e isenção de IVA via IOSS
- [Comissão Europeia: Âmbito territorial do IVA e das alfândegas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores, Madeira e Irlanda do Norte
- [Universal Parcel Scraper: GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – formatos GP e SDC, estados, deteção de devoluções, relógios, cache e transportadora final
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato USPS e transição para a empresa de entrega ou para os CTT
:::
