---
title: Rastreio Anjun Express: encomendas da China, status e SAC
description: Rastreie encomendas da Anjun Express (Anjun Logistics): código AJ, status da China ao Brasil, alfândega e Remessa Conforme, novo e-mail do SAC e telefone.
slug: rastreio-anjun-express
published: 2026-11-23
updated: 2026-11-23
---

A Anjun Express (marca brasileira da Anjun Logistics) é uma transportadora especializada em comércio eletrônico internacional e entregas no Brasil. Fundada em 2011 na China, entrou no Brasil em 2019 e alcançou cobertura nacional em 2025. Ela cuida da coleta na China, embarque aéreo, desembaraço aduaneiro no Brasil e entrega final — pela própria malha de última milha e pontos de coleta ou repassando o pacote aos Correios após a liberação alfandegária. Você pode acompanhar remessas internacionais no [rastreador internacional da Anjun](https://anjuntrack.com/trajectory), conferir pacotes nacionais no [site da Anjun Express Brasil](https://anjunexpress.com/trackPackage) ou colar o código no campo acima.

## Código de rastreio Anjun Express: formato e onde encontrar

O código aparece nos detalhes do pedido no marketplace ou no e-mail de envio da loja (muito comum em compras internacionais da China entregues no Brasil). Os códigos próprios da Anjun têm 17 caracteres: `AJ`, seis dígitos de data (`AAMMDD`) e mais nove dígitos.

| Formato (inventado) | O que é |
| --- | --- |
| `AJ261015482917364` | Código da Anjun Logistics / Anjun Express: `AJ` + 6 dígitos de data + 9 dígitos. |

A Anjun mantém dois portais de consulta: [anjuntrack.com](https://anjuntrack.com/trajectory) acompanha encomendas internacionais desde a China, enquanto [anjunexpress.com](https://anjunexpress.com/trackPackage) cobre entregas no Brasil. Se o seu código começar com `BR` e tiver 15 caracteres, ele pertence à [SPX Express Brazil](carrier:spx-br); se tiver 15 dígitos iniciados por `8880`, é da [J&T Express Brazil](carrier:j-and-t-br); e se tiver 14 dígitos sem prefixo, costuma ser da [Jadlog](carrier:jadlog). Para outros padrões, veja [formatos de código de rastreio](guide:tracking-number-formats) (em português de Portugal).

## O que significam os status da Anjun Express

Nas compras vindas da China, o rastreamento costuma começar com mensagens em inglês e passar para o português assim que a carga entra no Brasil.

| Status da Anjun | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Objeto postado` | Pré-aviso eletrônico ou postagem inicial antes de o pacote chegar ao galpão da Anjun. | "Etiqueta criada" |
| `The goods have arrived at Anjun`; `Objeto coletado` como primeiro registro de um pacote enviado dentro do Brasil | O armazém da Anjun na China recebeu a mercadoria, ou a Anjun coletou o pacote com um remetente no Brasil. | "Recebida pela transportadora" |
| `The goods have been issued`, `Departed Facility in SHENZHEN`, `Arrived at Sort Facility HONG KONG INTERNATIONAL AIRPORT`, `Departed Facility in HONG KONG INTERNATIONAL AIRPORT`, `Arrived at Sort Facility Destination Airport` | Expedição na China, saída aérea por Shenzhen ou Hong Kong e chegada ao aeroporto de destino no Brasil. | "Em trânsito" |
| `Recebimento de cargas no armazém courier` | Recebido no terminal de courier no Brasil, antes da alfândega. | "Em trânsito" |
| `Entrada no armazém alfândegado de TFK`, `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, `Análise concluída - importação autorizada` | Entrada no armazém alfandegado, envio de dados à Receita Federal/órgãos anuentes e conclusão da análise aduaneira. | "Na alfândega" |
| `Pacote Liberado Aguadando a Coleta de Last Mile`, `Saída do Centro Internacional`, `Coletado por Last Mile`, `Objeto coletado`, `Objeto recebido pelos Correios do Brasil` | Encomenda liberada pela alfândega e entregue à malha de última milha da Anjun ou aos Correios. | "Em trânsito" |
| `O pacote chegou ao centro de trânsito`, `O pacote saiu do centro de trânsito`, `Objeto chegou ao ponto de entrega` | Trânsito entre centros brasileiros até a base local de distribuição (ainda não saiu com o entregador). | "Em trânsito" |
| `Objeto saiu para entrega ao destinatário` | O entregador saiu com o pacote para entrega hoje no seu endereço. | "Saiu para entrega" |
| `A entrega falhou` (por exemplo `Cliente ausente` ou `Endereço fora da rota`) | A entrega não pôde ser concluída naquele dia (ausência no local ou endereço fora de rota). | "Tentativa de entrega" |
| `A entrega falhou: Pacote não pertence à Base` | Erro de triagem: o pacote foi enviado para a base errada e será redirecionado internamente. | "Em trânsito" |
| `O pacote está a caminho do centro de trânsito para devolução.` | O pacote voltou a um centro de trânsito depois de entregas sem sucesso. Ele ainda pode sair para entrega de novo. | "Precisa de atenção" |
| `O pacote foi assinado`, `Objeto entregue ao destinatário` | Entrega registrada como concluída. | "Entregue" |

Atenção: `Objeto chegou ao ponto de entrega` indica que o pacote chegou à base operacional de entregas da Anjun na sua região — e não a uma loja para você retirar. Aguarde `Objeto saiu para entrega ao destinatário`.

## Rede da Anjun Express no Brasil e prazos de entrega

Na [página inicial da Anjun Express](https://anjunexpress.com/home), a empresa destaca First Mile, [Last-Mile](https://anjunexpress.com/lastMile), B2B, armazenagem, envio direto e Super Express (a página de Last-Mile cita um armazém alfandegado de 25.000 m² no GRU Airport), citando mais de 1.000 pontos de coleta, mais de 350 veículos e mais de 2.000 funcionários (a página para parceiros de [ponto de coleta](https://anjunexpress.com/collectionPoint) menciona mais de 500 pontos).

As páginas públicas da Anjun Express não publicam um número fixo de tentativas de entrega, prazo garantido de entrega ou prazo único de guarda para todas as lojas. Confira sempre a data estimada e as regras de tentativa na página do pedido onde você realizou a compra.

## Alfândega no Brasil e Programa Remessa Conforme

Como grande parte das encomendas da Anjun vem da China, elas passam pela fiscalização da Receita Federal sob as regras do [Programa Remessa Conforme (PRC)](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona):

- **Loja certificada no PRC:** os tributos são pagos no fechamento do pedido. Em compras de pessoa física até US$ 50 (valor aduaneiro incluindo frete e seguro), o Imposto de Importação é de 0%, mais ICMS estadual de 17% a 20%. Acima de US$ 50, o Imposto de Importação é de 60% com desconto de US$ 30 no valor do imposto, mais 17% a 20% de ICMS.
- **Loja não certificada:** cobra-se 60% de Imposto de Importação sem desconto (mesmo até US$ 50) mais 17% a 20% de ICMS na chegada ao Brasil, antes da liberação da entrega.

Enquanto constar `Entrada no armazém alfândegado de TFK` ou `Informações enviadas para análise da autoridade aduaneira/órgãos anuentes`, a carga aguarda análise aduaneira. Se houver exigência de documentos ou comprovação de valor, verifique as notificações oficiais dentro do marketplace ou confirme com o atendimento aduaneiro da Anjun. Veja o guia sobre [encomendas na alfândega](guide:customs) (em português de Portugal).

## Encomenda Anjun parada, falha na entrega ou extraviada

Em compras de marketplace, quem responde por reembolso, reenvio ou contestação de pedido é a plataforma ou o vendedor.

**Vários dias sem atualização.** Entre o embarque em Hong Kong (`Departed Facility in HONG KONG INTERNATIONAL AIRPORT`) e o registro no armazém courier no Brasil, é normal haver um intervalo de vários dias sem novos bipes por causa do voo e da fila alfandegária. Confira [rastreio que não atualiza](guide:tracking-not-updating) (em português de Portugal).

**Erro de base (`Pacote não pertence à Base`).** Apesar de começar com `A entrega falhou`, essa mensagem significa apenas que o pacote foi separado na base errada e será reencaminhado antes da tentativa real no seu endereço.

**Entregue, mas não recebido.** Verifique primeiro com moradores, vizinhos e na portaria do prédio; depois, acione o suporte do marketplace e o SAC da Anjun. Veja [encomenda entregue mas não recebida](guide:delivered-not-received) (em português de Portugal).

**Avaria, extravio ou devolução.** Fotografe a embalagem e a etiqueta e avise o vendedor o quanto antes; consulte [encomenda danificada](guide:damaged-parcel) e [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender) (ambos em português de Portugal).

## Contato e novo e-mail do SAC da Anjun Express

Em aviso publicado em [anjunexpress.com/home](https://anjunexpress.com/home), a Anjun Express informa que o antigo endereço `sac@anjunexpress.com.br` foi desativado a partir de 12/08/2026. Os canais oficiais ativos no Brasil são:

- **Telefone nacional (SAC):** `(11) 5026-7008`, de segunda a sexta-feira, das 8h às 17h30 (exceto feriados).
- **Novo e-mail do SAC:** `sac@anjun.com.br`.
- **Chat online:** disponível no site [anjunexpress.com](https://anjunexpress.com/home).
- **Atendimento de remessas expressas internacionais / alfândega:** `atendimento.aduaneiro@anjun.com.br`.
- **Ouvidoria de remessas expressas internacionais:** `ouvidoria.aduaneira@anjun.com.br`.
- **Mediação de consumo:** se um problema com empresa participante não for resolvido pelo SAC, consumidores com conta gov.br prata ou ouro podem registrar reclamação gratuita no [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1), com prazo de resposta de até 10 dias.

> Cuidado com mensagens falsas por SMS ou WhatsApp cobrando taxa para liberar encomendas da Anjun na alfândega. Antes de fazer qualquer pagamento, confira o pedido no aplicativo da loja ou confirme pelo e-mail `atendimento.aduaneiro@anjun.com.br`. Veja o guia sobre [mensagens falsas de entrega](guide:delivery-scam-texts) (em português de Portugal).

## Rastrear uma encomenda Anjun Express com o Peek

O [Peek](/) identifica automaticamente a Anjun Logistics a partir de códigos `AJ` de 17 caracteres (com data válida `AAMMDD`) e de links colados de `anjuntrack.com` (`/trackQuery?t=` ou `/trajectory?orderNum=`) ou `anjunexpress.com/trackPackage?tracking=` contendo um código `AJ`. Se precisar ajustar a transportadora manualmente, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela do pacote e escolha Anjun Logistics.

O Peek consulta primeiro o `anjuntrack.com` para remessas internacionais e, se ainda não houver registros lá, consulta também o `anjunexpress.com` para cobrir pacotes nacionais no Brasil. Você vê a etapa atual ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Tentativa de entrega", "Precisa de atenção", "Entregue"), o histórico em inglês e português, o motivo de eventual tentativa sem sucesso (como `Cliente ausente`), a cidade e o estado no Brasil (endereços de rua são removidos), o país de destino e os horários locais (horário da China na origem e horário brasileiro no Brasil, convertidos para o fuso do seu aparelho). O Peek descarta barra de progresso, endereço residencial, coordenadas, nome do motorista, frases com telefone/e-mail inseridas nas observações, assinaturas e fotos; previsão de entrega, peso e eventual código local de repasse não são fornecidos pelo rastreio da Anjun.

Com o pacote aberto na tela, ou no dia da entrega com as notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a Anjun mostra `Objeto saiu para entrega ao destinatário`, até a cada 2 minutos a partir das 6h no horário local, enquanto o rastreio da própria Anjun responder. Fora disso, de hora em hora. Depois de 12 horas só com "Etiqueta criada" ou "Recebida pela transportadora", ou de dois dias sem novidade, consulta de hora em hora em qualquer caso, e a cada 6 horas depois de sete dias em "Etiqueta criada", a menos que você esteja com o pacote aberto ou que a entrega esteja prevista para esse dia. Quando o rastreio da Anjun não responde e o Peek acompanha o pacote só por serviços de rastreamento, consulta de hora em hora, a cada 3 horas depois de um dia sem novidade e até a cada 15 minutos no dia em que o pacote sai para entrega, com o pacote aberto ou as notificações ativadas.

Quando o registro mais recente tem quatro dias e a pacote continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com o dia desse registro, por exemplo "Sem atualizações desde seg 12 out". A página da pacote mostra então "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Anjun Logistics." Depois de três semanas sem registro novo, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Anjun Logistics."

Escolha o nível de avisos entre "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas monitora o rastreio e não faz liberação alfandegária nem entra em contato com a Anjun Express por você.

## Perguntas sobre o rastreio Anjun Express

### Por que o rastreio da Anjun mistura inglês e português?

Encomendas vindas da China começam com registros em inglês nos galpões chineses e no aeroporto de Hong Kong; quando chegam ao Brasil para desembaraço aduaneiro e entrega local, os status passam a sair em português.

### O que significa `A entrega falhou: Pacote não pertence à Base`?

Significa que o pacote chegou por engano à base de distribuição errada da Anjun, e não que o entregador passou na sua casa. A transportadora reencaminha a encomenda para a base correta, por isso o Peek a mantém em "Em trânsito".

### O e-mail `sac@anjunexpress.com.br` ainda funciona?

Não. A Anjun Express desativou `sac@anjunexpress.com.br` a partir de 12/08/2026. Os canais corretos são `sac@anjun.com.br`, o telefone `(11) 5026-7008` (segunda a sexta, 8h às 17h30) e `atendimento.aduaneiro@anjun.com.br` para temas alfandegários.

### A Anjun pode entregar meu pacote pelos Correios?

Sim. Em parte das encomendas internacionais, após a liberação na alfândega surge o status `Objeto recebido pelos Correios do Brasil`, indicando que os Correios assumem o trecho final até o seu endereço.

:::sources
- [Anjun Express: Página inicial](https://anjunexpress.com/home) – serviços no Brasil, estrutura, SAC `(11) 5026-7008`, `sac@anjun.com.br`, e-mails aduaneiros e aviso de desativação de `sac@anjunexpress.com.br` (12/08/2026)
- [Anjun Express: Sobre Nós](https://anjunexpress.com/aboutUs) – fundação em 2011, entrada no Brasil em 2019 e cobertura nacional em 2025
- [Anjun Express: Rastrear pacote](https://anjunexpress.com/trackPackage) – portal brasileiro de rastreamento
- [AnjunTrack: Rastreamento internacional](https://anjuntrack.com/trajectory) – portal de rastreamento China–Brasil
- [Anjun Express: Last-Mile](https://anjunexpress.com/lastMile) – operação brasileira de última milha
- [Anjun Express: Pontos de coleta](https://anjunexpress.com/collectionPoint) – rede de pontos parceiros
- [Receita Federal: Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/programa-remessa-conforme-o-que-e-como-funciona) – alíquotas de Imposto de Importação e ICMS
- [Consumidor.gov.br](https://www.consumidor.gov.br/pages/conteudo/publico/1) – plataforma pública de reclamações (prazo de 10 dias, conta gov.br prata ou ouro)
- [Universal Parcel Scraper: anjun](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/anjun) – detecção do código `AJ`, consulta nos dois portais, mapeamento de status, remoção de dados pessoais e frequência de atualização
:::
