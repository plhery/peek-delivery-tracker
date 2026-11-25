---
title: Rastreamento CTT Portugal: códigos, status e alfândega
description: Rastreie encomendas dos CTT de Portugal: formatos de código, status do Seguir objeto, prazos de retirada e Locky, taxas alfandegárias e atendimento.
slug: rastreamento-ctt-portugal
published: 2026-11-24
updated: 2026-11-24
---

Os CTT (CTT – Correios de Portugal) são o serviço postal nacional de Portugal, com um braço expresso chamado CTT Expresso. Você pode rastrear um volume pelo "Seguir objeto" em [ctt.pt](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) (que tem versão em inglês), pelo app dos CTT ou colando o código na caixa acima. O número chega por SMS ou e-mail dos CTT, em aviso impresso ou enviado pelo remetente.

## Como é o código de rastreamento dos CTT

A maioria dos códigos dos CTT tem duas letras para o serviço, nove dígitos e duas letras do país emissor. Alguns envios usam um código numérico de 22 ou 25 dígitos.

| Formato (fictício) | O que é |
| --- | --- |
| `RR482910576PT` | Código de rastreamento postal dos CTT. O último dos nove dígitos é um dígito verificador. |
| `RR482910576ES` | Mesmo formato, outro país: emitido pelo correio daquele país (neste exemplo, os [Correos](carrier:correos-spain) da Espanha). |
| `0048290048291057364918` | 22 dígitos: em ctt.pt, um envio originado na Espanha. |
| `0048290048291057364918001` | 25 dígitos. |

Os formatos de 22 e 25 dígitos também pertencem à [CTT Express](carrier:ctt-express), a transportadora de operação espanhola entre Espanha e Portugal, com rastreamento próprio em cttexpress.com.

Digite o código sem espaços: a ajuda em inglês dos CTT explica que `Item not found` indica que o objeto não foi identificado na rede. Não recebeu SMS nem e-mail com código dos CTT? Provavelmente o envio não possui rastreamento. Veja mais em [como encontrar seu código de rastreamento](guide:find-tracking-number).

## O que significam os status dos CTT

O Seguir objeto oferece versões em português de Portugal e em inglês. Estes são os textos dos CTT registrados pelo Peek e pela ajuda oficial, incluindo dois rótulos da página de ajuda em inglês:

| Status dos CTT | O que significa |
| --- | --- |
| `Aguarda entrada nos CTT` | Os CTT receberam os dados do remetente, mas ainda não receberam o volume. |
| `Aceite` | Aceito pelos CTT ou pelo correio de origem: o percurso começou. |
| `Em exportação` | Em trâmite aduaneiro no país de origem. |
| `International dispatch` | Dia em que deixou o país de origem. |
| `International reception` | Dia em que entrou no país de destino. |
| `Em importação` | Na alfândega do país de destino. |
| `Em trânsito` | Circulando entre centros; pode aparecer várias vezes. |
| `Em entrega` | Saiu hoje com o carteiro ou entregador. |
| `Entrega não conseguida` | Entrega não realizada; procure aviso na caixa de correio, SMS ou e-mail. |
| `No ponto de entrega` | Aguardando retirada num ponto de entrega. |
| `Em espera` | Aguardando instruções ou data agendada de entrega. |
| `Entregue` | Entregue. |
| `Devolvido` | Devolvido ao remetente. |

`Em importação` indica a alfândega portuguesa, onde pode haver cobrança de taxas (veja abaixo). `Não entregue` também significa tentativa de entrega sem sucesso.

## Encomenda não entregue nos CTT: avisos, pontos de retirada e Locky

Se ninguém atender, o destino do pacote depende do serviço contratado pelo remetente: ele vai para um ponto de entrega (Loja CTT, Ponto CTT, Cacifo Locky ou agente Payshop), ganha nova tentativa, aguarda instruções do remetente ou retorna. A Encomenda Postal segue diretamente para uma Loja ou Ponto CTT, salvo se o remetente pagou entrega em domicílio.

O aviso dos CTT chega por SMS, e-mail ou papel e informa local e prazo. Prazos em dias úteis contados a partir do dia seguinte ao aviso:

| Objeto | Dias úteis para retirar |
| --- | --- |
| Cartas e correspondência registrada | 6 |
| Encomenda postal nacional | 10 |
| Encomenda postal internacional | 15 |
| Encomendas Expresso | 5 (alguns produtos 10) |

Encomendas postais podem ter a guarda prorrogada por até 30 dias. Leve o aviso e documento oficial (Cartão de Cidadão, passaporte, autorização de residência ou carteira de motorista, entre outros), ou informe o código e apresente o documento no balcão.

**Cacifos Locky** guardam o pacote por 5 dias. Os CTT enviam o PIN por SMS ou e-mail; a liberação para retirada pode levar até 48 horas após o rastreamento registrar a chegada.

**Códigos Expresso iniciados por `D`** (Para Amanhã, Em Dois Dias) permitem alterações no Seguir objeto ou no app dos CTT: endereço, ponto de retirada, data, nova tentativa ou faixa horária. Você paga (uma nova data custa €5,01 mais IVA), e nada pode ser alterado depois que o objeto já aguarda num ponto.

**CTT Express** faz até duas tentativas, das 9h às 19h em dias úteis. Seus pontos Collectt Express guardam por 10 dias e seus lockers por 5 dias.

## Encomenda parada, na alfândega ou devolvida nos CTT

Os CTT observam que alguns países enviam dados internacionais de rastreamento com atraso ou não compartilham leituras. Se o pacote estacionar, contate o remetente que contratou o envio; veja [rastreamento que não atualiza](guide:tracking-not-updating).

**Alfândega.** Em compras de fora da UE sem IVA pago no ato, cobra-se IVA mais serviço de desalfandegamento dos CTT de €7 (mais IVA) até €150, e IVA, direitos aduaneiros e €14 entre €150 e €1.000. Desde 1º de julho de 2026, compras até €150 também pagam direito aduaneiro de €3 por tipo de produto. Desde 1º de novembro de 2026, aplica-se ainda a taxa de manuseio da UE de €2 por tipo de produto, qualquer que seja o valor, devida pelo declarante aduaneiro, acrescida de IVA; se não foi paga no checkout, você paga na chegada a Portugal. O prazo para desalfandegar é de 20 dias após a chegada em Portugal, sob pena de devolução definitiva ao remetente. Veja [encomendas retidas na alfândega](guide:customs).

**Reclamações** podem ser abertas pelo formulário dos CTT, pelo telefone ou numa Loja ou Ponto CTT em até um ano da postagem (nacional) ou seis meses (internacional). As condições do CTT Expresso dão ao remetente 8 dias após a entrega para reclamar de danos e 30 dias para extravio ou atraso.

**Devoluções** não podem ser canceladas: acerte reenvio ou reembolso com o remetente. Volumes que não podem ser entregues nem devolvidos seguem ao Refugo Postal, recuperáveis entre 20 dias e 3 meses via suporte.

## Atendimento e telefone dos CTT

Canais oficiais dos CTT:

- **Telefone:** 21 047 16 16 (do exterior, +351 21 047 16 16), dias úteis das 8h30 às 19h30, tarifa de rede fixa. Nenhuma linha em inglês é indicada.
- **Formulário de contato** em [ctt.pt](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto), respondido em dias úteis das 8h30 às 19h30.
- **Presencialmente** em qualquer Loja ou Ponto CTT.
- **CTT Express (Espanha):** 900 841 352 (grátis), 916 602 200 ou ccaweb@cttexpress.com, dias úteis das 9h às 19h.

> Os CTT alertam sobre SMS e e-mails falsos sobre entregas não realizadas ou suspensas e encomendas retidas até o pagamento de uma taxa. Todos os links oficiais começam com www.ctt.pt: não clique em outros endereços e apague a mensagem.

## Como rastrear encomendas dos CTT no Peek

O [Peek](/) reconhece de imediato os códigos de rastreamento postal dos CTT (duas letras, nove dígitos, `PT`, com dígito verificador válido) e lê diretamente o rastreador dos CTT; links de ctt.pt com `objects=` também funcionam. Se um dígito estiver errado, o verificador falha e o Peek não reconhece nenhuma transportadora — confira o código. Outros formatos dos CTT não são consultados diretamente nos CTT: o Peek recorre aos serviços universais de rastreamento.

O Peek mostra o status e as 20 movimentações mais recentes com local aproximado (centro ou ponto) no mapa, além do código de status dos CTT. Ele omite o remetente (quando os CTT informam o destinatário, o nome aparece em "Destinatário" só para quem adicionou o pacote, nunca num link compartilhado), e os CTT não fornecem dia previsto de entrega. Os horários são lidos no fuso de Lisboa — ou dos Açores para as ilhas — e exibidos no fuso do seu dispositivo.

Os códigos de 22 e 25 dígitos da CTT Express (`00`, quatro dígitos, `00`, 14 dígitos e `001` no de 25) vão para a entrada separada da CTT Express no Peek, que mostra histórico sem locais, previsão de entrega, peso, horário da entrega e textos em espanhol como `El envío fue entregado correctamente`.

O Peek verifica até a cada 10 minutos, ou a cada 2 minutos a partir das 6h no horário local no dia em que os CTT registram saída para entrega, enquanto o próprio rastreador dos CTT responde (pelos serviços de rastreamento, a cada 15 minutos). Ele pode avisar quando o status muda. Após dois dias sem novidades (ou 12 horas ainda em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora.

## Perguntas frequentes sobre rastreamento CTT

### Onde está minha encomenda dos CTT?

A linha mais recente no Seguir objeto mostra a etapa atual: `Em entrega` indica que saiu na rota de hoje, e `No ponto de entrega` indica que aguarda retirada.

### O que é um ponto de entrega dos CTT?

É uma Loja CTT (agência postal), Ponto CTT (comércio parceiro), Cacifo Locky ou agente Payshop. O aviso dos CTT informa exatamente qual local guarda o seu pacote.

### O atendimento dos CTT funciona em inglês?

Os CTT não listam uma linha específica em inglês. Ligue para +351 21 047 16 16 em dias úteis, das 8h30 às 19h30, ou envie mensagem pelo formulário de contato.

### CTT Express e CTT são a mesma coisa?

Eles têm rastreamentos separados: a CTT Express, transportadora de gestão espanhola entre Espanha e Portugal, usa cttexpress.com; já as encomendas postais e Expresso dos CTT são acompanhadas em ctt.pt.

:::sources
- [CTT: Results in Seguir objeto (English)](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto?language_id=1) – status em inglês e Item not found
- [CTT: Resultados do Seguir objeto](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/resultados-que-podem-aparecer-na-pesquisa-do-seguir-objeto) – significado dos status
- [CTT: Seguir objeto](https://www.ctt.pt/feapl_2/app/open/objectSearch/objectSearch.jspx?request_locale=pt) – rastreador e idiomas
- [CTT: Como seguir uma entrega](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/como-seguir-uma-entrega) – aplicativo CTT
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/empresas/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – formatos de código
- [CTT: Não tenho código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/alterar/a-entrega-vem-a-caminho-mas-nao-tenho-codigo-de-envio-o-que-posso-fazer) – sem código
- [CTT: Entrega não conseguida](https://www.ctt.pt/ajuda/particulares/receber/problemas-na-entrega/entrega-nao-conseguida) – entregas não realizadas
- [CTT: O que precisa de levar consigo](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/o-que-precisa-de-levar-consigo) – documento para retirada
- [CTT: Locky](https://www.ctt.pt/ajuda/particulares/receber/cacifos-locky/como-levantar-uma-encomenda) – cacifos Locky
- [CTT: Prazo para levantar](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/prazo-para-levantar) – prazos de guarda
- [CTT: Alterar uma entrega Expresso](https://www.ctt.pt/particulares/receber/seguir-ou-alterar-uma-entrega/alterar-uma-entrega-expresso) – alterações na entrega
- [CTT: Serviços cobrados](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/taxas-e-servicos-cobrados/servicos-cobrados) – taxas de desalfandegamento
- [CTT: Isenção de direitos](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – direito de €3; taxa de manuseio de €2 mais IVA, paga na chegada se não foi paga no checkout
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por tipo de produto qualquer que seja o valor, devido pelo declarante
- [CTT: Prazo para desalfandegar](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) – prazo de 20 dias
- [CTT: Condições Expresso](https://www.ctt.pt/transversais/condicoes-gerais-transporte-encomendas-expresso) – prazos de reclamação
- [CTT: Refugo Postal](https://www.ctt.pt/ajuda/particulares/receber/levantar-correspondencia-ou-encomendas-/recuperar-o-correio-que-esta-no-refugo-postal) – objetos não reclamados
- [CTT: Reclamações](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/reclamacoes) – canais e prazos de reclamação
- [CTT: Linhas de apoio](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/linhas-de-apoio) – telefone e horários
- [CTT: Formulário de contacto](https://www.ctt.pt/ajuda/particulares/apoio-ao-cliente/contactos/formulario-de-contacto) – formulário oficial
- [CTT: Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – mensagens falsas
- [CTT Express: Envíos a Portugal](https://www.cttexpress.com/envios/portugal) – tentativas de entrega
- [CTT Express: Horarios](https://www.cttexpress.com/preguntas-frecuentes/horarios) – horários de entrega
- [CTT Express: Envío y recogida](https://www.cttexpress.com/red-collectt-express/envio-y-recogida) – pontos Collectt e lockers
- [CTT Express: Cómo contactar](https://www.cttexpress.com/preguntas-frecuentes/como-contactar-con-ctt) – contatos da CTT Express
- [Wikipedia: CTT Correios de Portugal](https://en.wikipedia.org/wiki/CTT_Correios_de_Portugal) – serviço postal nacional
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê
:::
