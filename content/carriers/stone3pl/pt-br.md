---
title: Rastreio Stone3PL: códigos, status e entrega internacional
description: Rastreie encomendas Stone3PL vindas da China: formatos de código, significado de cada status, quem entrega no seu país, alfândega e contato.
slug: stone3pl-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Stone3PL é uma empresa logística sediada em Shenzhen, na China, integrante do Stone Group. Ela transporta mercadorias para vendedores on-line e outras empresas da China para o mundo, em parceria com outras operadoras logísticas. Se uma loja enviou seu pedido pela Stone3PL, consulte o código na [página de rastreio da Stone3PL](https://stone3pl.com/?route=services/track), em inglês, ou cole-o na caixa acima. O código vem do vendedor, junto com os dados de envio do pedido.

## Formato do código de rastreio Stone3PL

A Stone3PL não publica um formato de código. O Peek associa um formato à Stone3PL: a letra `S` seguida de 18 dígitos. Os dois exemplos abaixo são inventados:

| Código | O que é |
| --- | --- |
| `S482917365428193642` | `S` e 18 dígitos. O Peek consulta a Stone3PL para números com esse formato. O formato sozinho não garante que o pacote pertença à Stone3PL. |
| `S48291736542819364` | Apenas 17 dígitos: não corresponde ao formato que o Peek vincula à Stone3PL. Confira se falta um dígito, ou cole mesmo assim. |

No Peek, letras minúsculas, espaços e hifens não importam. O rastreador da Stone3PL aceita até 40 códigos de uma vez, um por linha, mantendo apenas letras, dígitos e hifens e convertendo minúsculas em maiúsculas.

**Onde encontrar.** No e-mail de envio do vendedor ou na página do pedido. Se não estiver lá, veja [como encontrar seu código de rastreio](guide:find-tracking-number).

**Outros códigos.** A página da Stone3PL lista empresas que seu rastreador acompanha, como DHL Express, UPS, USPS, China Post e China EMS (ePacket). O resultado também pode informar a transportadora que entrega no seu país junto com o código próprio dela, que pode ser consultado diretamente no site dessa empresa.

## Significado dos status de rastreio Stone3PL

As linhas de rastreio da Stone3PL aparecem em inglês, com data e hora mas sem local. A página de resultados mostra primeiro as atualizações do país de destino e depois as do início da viagem. Veja as mensagens que o Peek conhece:

| A Stone3PL exibe | O que significa para você | O que o Peek mostra |
| --- | --- | --- |
| `Order Submited.` | O vendedor enviou o pedido à Stone3PL, que pode ainda não estar com o pacote (a grafia é original da Stone3PL). | "Etiqueta criada" |
| `Shipment information received` | A Stone3PL recebeu os dados do envio. | "Etiqueta criada" |
| `Processing information input` | A Stone3PL está cadastrando os dados do pacote. | "Etiqueta criada", ou "Em trânsito" após `Pickup Scan` |
| `Pickup Scan` | A encomenda foi coletada e escaneada, já estando a caminho. | "Recebida pela transportadora" |
| `facility - Outbound` | O pacote deixou um armazém ou centro de triagem. | "Em trânsito" |
| `Arrived at domestic terminal station` | Chegou a um terminal (a linha não informa o país). | "Em trânsito" |
| `Port of departure` | Está no porto ou aeroporto de saída, aguardando embarque. | "Em trânsito" |
| `International shipment release - Export` | Liberada pela alfândega de exportação, seguindo viagem. | "Em trânsito" |
| `Port of departure - Departure` | Partiu do porto ou aeroporto de origem. | "Em trânsito" |
| `Port of destination - Arrival` | Chegou a um porto ou aeroporto no país de destino. | "Em trânsito" |
| `International shipment release - Import` | Liberada pela alfândega de importação, seguindo viagem. | "Em trânsito" |
| `Pick up by local carrier at destination port` | A transportadora local do seu país coletou o pacote no porto ou aeroporto — não significa que você deva buscá-lo. | "Em trânsito" |
| `Batch delivery to carrier` | A Stone3PL repassou o pacote em lote à transportadora de entrega. | "Em trânsito" |

Linhas do país de destino podem usar outras redações. O Peek reconhece palavras comuns como entrega ou alfândega; qualquer linha não reconhecida permanece no histórico mantendo a última etapa compreendida.

**Status geral.** O rastreador da Stone3PL também atribui um status geral ao pacote. Quando indica pronto para retirada, entregue ou problema, o Peek exibe "Pronta para retirada", "Entregue" ou "Precisa de atenção" mesmo antes de aparecer uma linha detalhada.

**Horários.** A Stone3PL informa horários sem fuso. O Peek interpreta as linhas do início do trajeto no horário da China (UTC+8), e as linhas do país de destino e `International shipment release - Import` no horário desse país (em um de seus fusos quando há mais de um), convertendo tudo para o fuso do seu dispositivo.

## Quem entrega encomendas Stone3PL

A Stone3PL informa atuar em parceria com provedores logísticos globais e manter armazéns nas Américas (NORAM), Ásia-Pacífico (APAC) e Europa, Oriente Médio e África (EMEA).

Suas páginas não dizem qual empresa entrega em cada país: confira na página de resultado da Stone3PL a transportadora final e o código dela. Tentativas de entrega, avisos e pontos de retirada seguem as regras dessa transportadora local. Veja [entregas não realizadas](guide:missed-delivery).

**Impostos e alfândega.** A Stone3PL oferece a vendedores frete DDP (*Delivered Duty Paid*), com desembaraço aduaneiro DDP. Apenas o vendedor pode confirmar se o seu pedido foi enviado nessa modalidade.

**Na União Europeia (Irlanda, por exemplo).** Compras on-line de fora da UE pagam tarifa aduaneira de € 3 por item em remessas de até € 150 desde 1º de julho de 2026, além de taxa de manuseio da União de € 2 por item independentemente do valor desde 1º de novembro de 2026. Um item reúne os produtos com mesma classificação tarifária, descrição e, quando exigida, origem. As páginas da Stone3PL não informam como esses valores são recolhidos. No Reino Unido, EUA, Canadá ou Austrália, valem as regras locais: consulte [encomendas retidas na alfândega](guide:customs).

## Encomenda Stone3PL sem atualização, perdida ou devolvida

A Stone3PL atende às empresas que despacham com ela e não mantém páginas de ajuda para destinatários; para atraso, extravio, dano ou devolução, fale primeiro com o vendedor.

**Nada ainda.** O Peek continua verificando. Exibe "Aguardando Stone3PL" se você selecionou Stone3PL, ou "Ainda sem histórico de rastreamento" se adicionou sem transportadora. Após dois dias sem resposta em um pacote sem transportadora, o Peek sinaliza o pacote ("Aguardando a primeira atualização da transportadora"): confira o código com o vendedor. Se você escolheu Stone3PL e dois dias depois ela ainda não reconhece o número (sem outro retorno), o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a consultar diariamente, e semanalmente depois de uma semana desde que você o adicionou.

**Apenas `Order Submited.` ou `Shipment information received`.** A Stone3PL recebeu os dados eletrônicos, mas talvez ainda não tenha o pacote físico. Se não avançar por vários dias, pergunte ao vendedor se já foi despachado.

**Sem atualização há dias.** Quando um pacote em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito" fica quatro dias sem nova linha, o Peek exibe o aviso com a data da última movimentação (por exemplo, "Sem atualizações desde seg 12 out") e lembra que pacotes costumam ficar sem atualizações em voos longos, na alfândega ou nos fins de semana. Veja [por que o rastreio para de atualizar](guide:tracking-not-updating) e [rastrear encomendas da China](guide:tracking-from-china).

**"Precisa de atenção".** A Stone3PL sinalizou algum problema. Leia a linha mais recente e pergunte ao vendedor quais são os próximos passos.

**Entregue mas não recebida.** Procure ao redor da residência e pergunte aos vizinhos; depois avise o vendedor. Veja [entregue mas não recebida](guide:delivered-not-received).

**Perdida, danificada ou devolvida.** O site da Stone3PL não orienta destinatários sobre pedidos de indenização ou devoluções; sua página inicial oferece aos vendedores um serviço de devolução e reenvio. Peça reembolso ou novo envio ao vendedor. Veja [encomendas perdidas](guide:lost-parcel), [encomendas danificadas](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

**Mensagem cobrando pagamento.** Antes de pagar qualquer taxa recebida por SMS ou e-mail, leia [golpes por SMS de entrega](guide:delivery-scam-texts).

## Contato Stone3PL

Na [página de contato da Stone3PL](https://stone3pl.com/index.php?route=services/contact_us), para sua matriz em Shenzhen ("STONE3PL Marketing Dpt"):

- **E-mail:** support@stone3pl.com (o mesmo consta na política de privacidade).
- **Telefone:** 86-0755-81466633, número em Shenzhen, China. A empresa não informa horário nem idiomas de atendimento.
- **Endereço:** Ground Floor, Building C5, Xin'an Industry Co., No.2 Industrial Zone, Gushu 2nd Rd., Baoan, Shenzhen, Guangdong Province, China.

Sobre o seu pedido, fale primeiro com o vendedor: foi ele quem contratou a Stone3PL.

## Acompanhe sua encomenda Stone3PL no Peek

O [Peek](/) acompanha encomendas Stone3PL, mas não confirma a transportadora apenas pela aparência do número. Ao colar um código com `S` e 18 dígitos, o Peek mostra "Consultando serviços de rastreamento…" enquanto consulta a Stone3PL. Se a Stone3PL estiver com o pacote, a linha da transportadora diz que a Stone3PL "está com esta encomenda", e o Peek o acompanha por lá. Um link do rastreador da Stone3PL com um número após `nums=` identifica a Stone3PL imediatamente ("Transportadora detectada").

Para qualquer outro número, cole normalmente: o Peek acompanha mais de 4.500 transportadoras. Quando o formato coincide de forma fraca, mostra "Consultando serviços de rastreamento…"; números sem formato reconhecido são consultados após você adicionar. Se o Peek não nomear a transportadora, toque em "Trocar" e escolha Stone3PL em "Todas as transportadoras", ou use "Trocar transportadora" em um pacote já adicionado.

O Peek lê o status geral, cada linha com horário, países de origem e destino e a transportadora final com o respectivo número quando informados, omitindo telefones. A Stone3PL não fornece previsão de entrega, peso, locais de leitura nem comprovante de entrega.

Quando a Stone3PL informa a transportadora de entrega e seu número, o Peek também consulta essa empresa. Com um código postal (duas letras, nove dígitos, duas letras) sem empresa nomeada, consulta o correio do país de destino — como [USPS](carrier:usps) nos EUA, [Royal Mail](carrier:royal-mail) no Reino Unido, An Post na Irlanda, [Canada Post](carrier:canada-post) ou [Australia Post](carrier:australia-post). Com outro formato, consulta a transportadora exclusiva daquele padrão, se houver. Assim que ela apresentar leitura tão recente quanto a da Stone3PL, o Peek passa a acompanhar lá preservando o histórico da Stone3PL, exibindo antes mesmo disso o aviso de entrega pela transportadora final (como "Entrega por USPS") ou "Anunciada a …, ainda não recebida" quando houver apenas pré-aviso. Sem transportadora final identificável, o Peek permanece na Stone3PL.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário do país de destino no dia da saída para entrega, enquanto o rastreador da Stone3PL responder. Nos demais casos, e após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta a cada hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". Quando outra transportadora assume, o Peek segue a frequência dela. Você pode ativar alertas de "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode entrar em contato com a Stone3PL ou o vendedor por você.

## Perguntas sobre o rastreio Stone3PL

### A Stone3PL é confiável?

A Stone3PL informa fazer parte do Stone Group, com sede em Shenzhen, mais de 10 anos de experiência e mais de 8.000 clientes corporativos, publicando endereço, telefone e e-mail. Como atende vendedores, dúvidas sobre seu pedido devem ser tratadas com a loja.

### Onde posso rastrear uma encomenda Stone3PL?

Na [página de rastreio da Stone3PL](https://stone3pl.com/?route=services/track) ou no Peek. Se o resultado da Stone3PL informar a empresa que entrega no seu país, você também pode consultar o código dela no próprio site dela.

### O que significa "Pick up by local carrier at destination port"?

Significa que a transportadora responsável pela entrega no seu país retirou sua encomenda no porto ou aeroporto de chegada — não quer dizer que você precise ir retirar o pacote.

### Quanto tempo a Stone3PL leva para entregar?

A Stone3PL não publica prazos de entrega. Baseie-se na estimativa informada pelo vendedor e procure-o em caso de atraso.

### Como entrar em contato com a Stone3PL?

Por e-mail em support@stone3pl.com ou telefone 86-0755-81466633, em Shenzhen, China. Sobre sua compra, procure primeiro o vendedor.

:::sources
- [Stone3PL: Home](https://stone3pl.com/) – logística de e-commerce da China para o mundo para vendedores, desembaraço DDP e Delivered Duty Paid, serviço de devolução e reenvio, armazéns em NORAM, APAC e EMEA
- [Stone3PL: Logistics tracking](https://stone3pl.com/?route=services/track) – rastreador em inglês, até 40 códigos um por linha, letras, dígitos e hifens em maiúsculas, empresas que o rastreador acompanha
- [Stone3PL: About us](https://stone3pl.com/index.php?route=services/about_us) – Stone Group, sede em Shenzhen, mais de 10 anos de experiência, mais de 8.000 clientes contratuais, parceiros logísticos globais
- [Stone3PL: Contact us](https://stone3pl.com/index.php?route=services/contact_us) – sede em Shenzhen, e-mail, telefone, endereço
- [Stone3PL: Privacy Policy](https://stone3pl.com/index.php?route=services/privacy) – e-mail de suporte
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tarifa de € 3 por item até € 150 desde 1º de julho de 2026 e taxa de manuseio da União de € 2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 por item qualquer que seja o valor, definição de item
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato do código, textos e etapas de rastreamento, status geral, horário da China, transportadora final, repasse
:::
