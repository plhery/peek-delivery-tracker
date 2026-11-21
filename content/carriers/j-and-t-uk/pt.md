---
title: Rastreio J&T Express UK: números JTUK e estados
description: Segue uma encomenda J&T Express UK: formato do número JTUK, significado de cada estado, alfândega no Reino Unido e o que fazer em caso de atraso ou perda.
slug: rastreio-jt-express-uk
published: 2026-11-21
updated: 2026-11-21
---

A J&T Express UK entrega encomendas no Reino Unido. Se uma loja enviou a tua compra pela J&T, o número de seguimento começa por `JTUK`: cola-o na caixa acima ou consulta-o no rastreio próprio da J&T em www.jtexpress.uk/tracking. O número vem da loja, no e-mail de envio ou na página da compra. Como o Peek não conseguiu ler o site da J&T Express UK, esta página baseia-se no que o código do Peek sabe sobre o rastreio da J&T e em fontes oficiais do Reino Unido.

## Número de seguimento J&T Express UK: como é

A guia da J&T Express UK tem `JTUK` seguido de 12 algarismos, 16 carateres ao todo. O Peek não conhece outra forma para ela. Exemplos com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `JTUK482910573628` | `JTUK` e 12 algarismos: uma encomenda J&T Express UK. O Peek identifica logo a J&T Express UK. |
| `JTUK48291057362` | Falta um algarismo: não corresponde à forma da J&T Express UK e o Peek não reconhece nenhuma transportadora. Verifica se falta um algarismo. |
| `JTDE482910573628` | `JTDE` e 12 algarismos: [J&T Express Germany](carrier:j-and-t-de), não a rede do Reino Unido. |
| `JTAU482910573628` | `JTAU` e 12 algarismos: [J&T Express Australia](carrier:j-and-t-au). |
| `JTE482910573628` | `JTE` e 12 algarismos: [J&T Express Middle East](carrier:j-and-t-middle-east). |
| `JT4829105736284` | `JT` e 13 algarismos: redes da J&T no Sudeste Asiático e na China ([J&T Express China](carrier:j-and-t-cn)). |

No peektracker.com, um número `JTUK` com um carácter a menos ou a mais mostra um aviso como: «Começa como um número de seguimento de J&T Express UK, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» Espaços, hífenes e minúsculas não importam no Peek: `jtuk 4829 1057 3628` lê-se igual à primeira linha. O catálogo do Peek também conhece a transportadora como J&T Express (UK), J&T Express UK Ltd e J&T UK.

**Onde encontrar.** No e-mail de envio da loja, na página da encomenda na loja ou marketplace, ou em qualquer mensagem sobre a entrega. Se não o encontrares, vê [onde encontrar o número de seguimento](guide:find-tracking-number) ou pergunta à loja.

## Estados de seguimento da J&T Express UK explicados

O rastreio da J&T apresenta cada registo como uma hora e uma frase, sem local. Estas são as frases registadas pelo código do Peek em encomendas reais da J&T Express UK, pela ordem habitual:

| O que diz a J&T | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `The parcel has been successfully picked up and is being prepared for transportation` | A J&T recolheu a encomenda do remetente e prepara-a para transporte. | «Aceite pela transportadora» |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination` | Chegou a um centro de triagem da J&T. | «Em trânsito» |
| `The parcel is dispatched from the transit center to the next destination` | Saiu desse centro para o destino seguinte. | «Em trânsito» |
| `The parcel has been collected and is on its way` | O estafeta recolheu a encomenda para a volta de entrega. Apesar das palavras, não é a recolha inicial: a J&T classifica-a como registo de entrega. | «Em distribuição» |
| `The parcel has been successfully delivered to the recipient` | Entregue. | «Entregue» |
| `The delivery address is incomplete or incorrect.` | Tentativa falhada: o estafeta não conseguiu entregar na morada indicada. No histórico guardado no código do Peek, surgiu horas após a saída para entrega, no mesmo dia. | «Tentativa de entrega» |
| `Delivery was attempted, but no one was available to receive the package at the address; the package was taken to a pickup location for the customer to collect.` | Não estava ninguém para receber a encomenda, pelo que foi levada para um ponto de recolha para levantares. | «Tentativa de entrega» |

Qualquer frase fora desta tabela segue as regras gerais do Peek: se não for reconhecida, aparece como a J&T a escreveu e conta como «Em trânsito», exceto se a encomenda já estiver em distribuição ou numa fase posterior, mantendo-se então o estado atual.

Se uma frase terminar com um número de apoio ao cliente ou um convite para ligar, o Peek omite essa parte. Também remove texto entre parênteses, números de telefone e nomes de quem assinou, recebeu ou recolheu a encomenda, bem como de estafetas, motoristas, destinatários ou remetentes, mantendo o resto.

## Entrega J&T Express UK: o que mostra o seguimento

Nos históricos no código do Peek, o estafeta da J&T sai para distribuição e entrega a encomenda, reporta um problema na morada ou leva-a a um ponto de recolha se não houver ninguém. O código do Peek não mostra passagem para outra transportadora: o rastreio da J&T acompanha a encomenda até à tua porta.

**Em distribuição.** `The parcel has been collected and is on its way` significa que a encomenda está na volta de hoje. Como o rastreio da J&T não indica janela horária nem dia previsto, o Peek também não pode mostrá-los.

**Problema na morada.** Após `The delivery address is incomplete or incorrect.`, confirma a morada da tua encomenda. Se faltar algum dado (como o número do apartamento ou o código postal), avisa logo a loja que contratou o envio. Vê [como mudar a morada de entrega](guide:change-delivery-address) e [entrega falhada](guide:missed-delivery).

**Tentativas, locais seguros e pontos de recolha.** Como o Peek não conseguiu ler o site da J&T Express UK, esta página não indica quantas tentativas a J&T faz, se deixa encomendas com vizinhos ou num local seguro, nem quanto tempo guarda uma encomenda não entregue. Pergunta à loja ou consulta as mensagens de entrega.

**Encomendas vindas de fora do Reino Unido.** Segundo o GOV.UK, se a compra veio do estrangeiro, a empresa que faz a entrega contacta-te se houver IVA (VAT), direitos aduaneiros ou taxas de entrega a pagar, enviando uma fatura com o valor exato. Normalmente guarda a encomenda cerca de 3 semanas e devolve-a ao remetente se não for paga. Em bens até 135 £ comprados por ti (exceto bens sujeitos a impostos especiais de consumo como álcool, tabaco ou cigarros eletrónicos), o vendedor já incluiu o IVA no preço. Acima de 135 £, pagas o IVA à transportadora antes da entrega ou no levantamento, além de eventuais direitos aduaneiros conforme a mercadoria e a origem. A Irlanda do Norte tem regras próprias. O Peek não conseguiu verificar como a J&T cobra encargos de importação. Mais em [encomendas na alfândega](guide:customs).

## Encomenda J&T Express UK atrasada, perdida ou devolvida

A loja escolheu a J&T, pelo que numa compra online a loja é o primeiro contacto. Nos termos da secção 29 do Consumer Rights Act 2015, os bens comprados a um comerciante permanecem por conta e risco do vendedor até chegarem às tuas mãos ou às de alguém que tenhas indicado, salvo se tiveres contratado por tua conta uma transportadora não proposta pelo vendedor.

**Sem movimento.** Uma encomenda em «Aceite pela transportadora» ou «Em trânsito» sem nova linha há quatro dias recebe um aviso no Peek («Sem atualizações há quatro dias») com ligação para a página de contacto da J&T Express UK («Pede ajuda a J&T Express UK»). Se continuar parada, fala com a loja. Vê [porque o seguimento não atualiza](guide:tracking-not-updating).

**Ainda sem seguimento.** Um número que o rastreio da J&T ainda não conhece aparece como «À espera de J&T Express UK». As lojas criam muitas vezes o número antes de entregarem a encomenda. Se dois dias depois a J&T continuar sem o reconhecer e nenhuma outra fonte tiver respondido, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e verifica depois uma vez por dia, e uma vez por semana passada uma semana desde que a adicionaste. Confirma o número com a loja e pergunta se a encomenda já seguiu mesmo.

**Entregue, mas nada chegou.** Verifica junto à porta, em casa e com os vizinhos, e avisa a loja: segundo essa lei, o risco continua a ser dela até à entrega efetiva, por isso pede reembolso ou novo envio. Mais em [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Pede à loja reembolso ou substituição com o teu número de seguimento. O Peek não conseguiu ler as regras de reclamação da J&T; vê [encomenda perdida](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

**Devolvida.** Se a encomenda voltar à loja após entrega falhada, cabe à loja tratar do reembolso ou novo envio. Vê [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto da J&T Express UK

Como o Peek não conseguiu ler o site da J&T Express UK, esta página não indica número de telefone, e-mail nem chat: o Peek só lista contactos lidos nas páginas oficiais da transportadora. O Companies House regista a sede social em `C/O Tmf Group 13th Floor, One Angel Court, London, United Kingdom, EC2R 7HJ`: trata-se da morada legal ao cuidado de outra empresa, não de um balcão de encomendas.

Começa pela loja que contratou a entrega ou usa a ajuda na página do pedido se compraste num marketplace. O rastreio da J&T está em www.jtexpress.uk/tracking.

## Seguir uma encomenda J&T Express UK com o Peek

O [Peek](/) identifica logo a J&T Express UK a partir de um número `JTUK` («Transportadora detetada») e consulta o rastreio da J&T só com o número, sem código postal nem conta. Uma ligação www.jtexpress.uk/tracking com `?q=` e o número também funciona; se trouxer outro tipo de número, o Peek lê-o como se o tivesses colado sozinho. Se não for um `JTUK`, cola-o na mesma: o Peek segue mais de 4500 transportadoras. Para um número `JT` com 13 algarismos, mostra «A verificar serviços de seguimento…» enquanto pesquisa. Escolher J&T Express UK em «Alterar» para outro formato não ajuda: o Peek indica que «não emite números como este».

O Peek mostra o estado, cada registo com a hora exata e a hora de entrega. A J&T indica cada registo como um instante exato, por isso o Peek mostra todas as horas no fuso horário do teu dispositivo, de verão e de inverno; no peektracker.com e nas apps para iPhone e Android, o histórico termina com «Horas no teu fuso horário:» e o respetivo nome. Os registos surgem no inglês da J&T. Como a J&T não indica local, dia previsto nem peso, o Peek não mostra mapa. O rastreio da J&T pode incluir comprovativo de entrega, que o Peek omite; num histórico muito longo, guarda os 100 registos mais recentes. «Abrir o site de J&T Express UK», na página da encomenda, abre o teu número no rastreio da J&T.

Enquanto a J&T não lê a encomenda, o Peek continua a perguntar com «À espera de J&T Express UK» (ou pede para verificares o número após dois dias). Quando o rastreio da J&T está inacessível ou desconhece o número, o Peek consulta serviços de seguimento e mostra o histórico deles nas suas palavras.

Enquanto tens a encomenda aberta no ecrã ou no dia da entrega com as notificações ativas, e enquanto o rastreio da J&T responde, o Peek verifica até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6h (hora local) no dia em que a J&T regista a saída para entrega; caso contrário, de hora a hora. Através de serviços de seguimento verifica de hora a hora, e até de 15 em 15 minutos no dia da distribuição enquanto a tens aberta ou com notificações ativas. Depois de dois dias sem um novo registo (12 horas enquanto mostra «Aceite pela transportadora»), verifica de hora a hora. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a J&T nem a loja por ti, alterar a morada nem marcar nova entrega.

## Perguntas sobre o rastreio J&T Express UK

### A J&T Express UK é legítima?

Sim. O Companies House lista `J&T EXPRESS UK LTD` como sociedade limitada privada ativa, constituída em 18 de dezembro de 2025, com atividades declaradas de transporte rodoviário de mercadorias, armazenagem e atividades postais. Os seus números começam por `JTUK` e o Peek lê diretamente o seu rastreio.

### O que significa «The parcel has been collected and is on its way» na J&T?

A tua encomenda está em distribuição. O estafeta recolheu-a para a volta de entrega; a J&T classifica essa linha como registo de entrega e o Peek mostra «Em distribuição». Não significa que acabou de sair do remetente.

### Porque diz a J&T que a minha morada está incompleta ou incorreta?

O estafeta não conseguiu entregar porque faltava informação ou não correspondia. Confirma a morada no pedido e avisa de imediato a loja com a morada completa e o número de seguimento.

### Porque não é encontrado o meu número J&T?

Confirma que começa por `JTUK` seguido de 12 algarismos: números de outras redes J&T, como `JTDE` na Alemanha, não pertencem à rede britânica. Se o número estiver certo, a J&T pode ainda não ter a encomenda e o Peek mostra «À espera de J&T Express UK». Se dois dias depois continuar sem o reconhecer e nenhuma outra fonte responder, o Peek pede para o verificares, escolheres outra transportadora ou eliminares a encomenda. Sem novidades após vários dias, pergunta à loja.

### Como contacto a J&T Express UK?

Fala primeiro com a loja que contratou a entrega. Como o Peek não conseguiu ler o site da J&T Express UK, esta página não apresenta telefone nem e-mail.

:::sources
- [Universal Parcel Scraper: J&T Express UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-uk) – nomes, formato `JTUK`, ligações de seguimento, página de ajuda `jtexpress.uk/contact`, frases do rastreio e etapas (incluindo envio para ponto de recolha após ausência), horas exatas, sem local/dia previsto/peso, comprovativo de entrega omitido, 100 registos mais recentes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de outras redes J&T, remoção de linhas de apoio ao cliente, parênteses, nomes e telefones, sem passagem a outra transportadora, serviços de seguimento de recurso
- [Companies House: J&T EXPRESS UK LTD](https://find-and-update.company-information.service.gov.uk/company/16920174) – sociedade privada limitada ativa, constituída em 18 de dezembro de 2025, atividade, sede social
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – fatura da transportadora, guarda de cerca de 3 semanas, IVA até 135 £ e acima, direitos aduaneiros, Irlanda do Norte
- [UK legislation: Consumer Rights Act 2015, section 29](https://www.legislation.gov.uk/ukpga/2015/15/section/29) – risco a cargo do comerciante até à posse física pelo consumidor
:::
