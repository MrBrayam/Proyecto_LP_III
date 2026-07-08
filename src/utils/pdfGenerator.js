import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { api } from '../api';

export const downloadReceiptGlobal = async (ventaId) => {
  if (!ventaId) return;
  try {
    const [ventaRes, detalles] = await Promise.all([
      api.getVenta(ventaId),
      api.getDetallesVenta(ventaId)
    ]);

    if (!ventaRes || !ventaRes.success || !ventaRes.venta) {
      alert('No se encontró la información del pedido.');
      return;
    }
    
    const venta = ventaRes.venta;
    const doc = new jsPDF();

    doc.setFont('helvetica', 'bold');
    doc.setFontSize(20);
    doc.setTextColor(30, 30, 36);
    doc.text('BELLARISTA SALON & BOUTIQUE', 14, 20);

    doc.setFont('helvetica', 'normal');
    doc.setFontSize(9);
    doc.setTextColor(117, 117, 119);
    doc.text('RUC: 20601234561', 14, 26);
    doc.text('Dirección: Av. La Marina 123, San Miguel, Lima', 14, 31);
    doc.text('Teléfono: 01-3456789 | contacto@bellarista.pe', 14, 36);

    doc.setDrawColor(197, 168, 128);
    doc.setFillColor(245, 239, 230);
    doc.rect(130, 12, 65, 26, 'FD');

    doc.setFont('helvetica', 'bold');
    doc.setFontSize(12);
    doc.setTextColor(176, 145, 104);
    doc.text('BOLETA DE VENTA', 135, 18);
    doc.text('ELECTRONICA', 142, 23);

    const ticketNum = venta.numero_ticket || `TK-${venta.id_ventas}`;
    doc.setFontSize(11);
    doc.setTextColor(30, 30, 36);
    doc.text(ticketNum, 142, 31);

    doc.setDrawColor(230, 230, 233);
    doc.line(14, 45, 195, 45);

    doc.setFont('helvetica', 'bold');
    doc.setFontSize(10);
    doc.text('DATOS DEL CLIENTE', 14, 52);

    doc.setFont('helvetica', 'normal');
    doc.setFontSize(9);
    const cli = venta.id_clientes || {};
    const cliNombre = cli.nombre_cliente ? `${cli.nombre_cliente} ${cli.apellidos_clientes || ''}` : 'Cliente General';
    const cliDoc = cli.numero_documento ? `${cli.tipo_documento || 'DOC'}: ${cli.numero_documento}` : 'Publico General';
    const cliDir = cli.direccion ? `${cli.direccion}, ${cli.distrito || ''}` : '-';
    const fecha = venta.fecha_venta ? new Date(venta.fecha_venta).toLocaleString() : new Date().toLocaleString();

    doc.text(`Cliente: ${cliNombre}`, 14, 58);
    doc.text(`Documento: ${cliDoc}`, 14, 63);
    doc.text(`Direccion: ${cliDir}`, 14, 68);
    doc.text(`Fecha Emision: ${fecha}`, 120, 58);
    doc.text('Metodo Pago: Pago Electronico', 120, 63);

    const headers = [['Item', 'Producto / Servicio', 'Cant.', 'Precio Unit.', 'Total']];
    const rows = detalles.map((d, index) => [
      index + 1,
      d.nombre_producto || 'Producto/Servicio',
      d.cantidad || 0,
      `S/ ${parseFloat(d.precio_unitario || 0).toFixed(2)}`,
      `S/ ${parseFloat(d.subtotal || 0).toFixed(2)}`
    ]);

    doc.autoTable({
      head: headers,
      body: rows,
      startY: 75,
      theme: 'striped',
      headStyles: { fillColor: [30, 30, 36], textColor: [255, 255, 255] },
      alternateRowStyles: { fillColor: [250, 250, 252] },
      styles: { font: 'helvetica', fontSize: 9 }
    });

    const finalY = doc.lastAutoTable.finalY + 10;

    doc.setFont('helvetica', 'normal');
    doc.setFontSize(9);
    const subVal = venta.subtotal != null ? parseFloat(venta.subtotal).toFixed(2) : '0.00';
    const igvVal = venta.impuesto != null ? parseFloat(venta.impuesto).toFixed(2) : '0.00';
    const totVal = venta.total != null ? parseFloat(venta.total).toFixed(2) : '0.00';

    doc.text('Subtotal:', 140, finalY);
    doc.text(`S/ ${subVal}`, 185, finalY, { align: 'right' });
    doc.text('IGV (18%):', 140, finalY + 5);
    doc.text(`S/ ${igvVal}`, 185, finalY + 5, { align: 'right' });

    doc.setFont('helvetica', 'bold');
    doc.setFontSize(10);
    doc.text('Total General:', 140, finalY + 12);
    doc.text(`S/ ${totVal}`, 185, finalY + 12, { align: 'right' });

    doc.setFont('helvetica', 'normal');
    doc.setFontSize(8);
    doc.setTextColor(117, 117, 119);
    doc.text('Esta es una representacion impresa de la boleta de venta electronica generada en Bellarista Storefront.', 14, finalY + 30);
    doc.text('Autorizado por SUNAT. Muchas gracias por su preferencia!', 14, finalY + 35);

    const pdfBlob = doc.output('blob');
    const pdfUrl = URL.createObjectURL(pdfBlob);
    window.open(pdfUrl, '_blank');
  } catch (err) {
    console.error(err);
    alert('Error al generar el PDF de la boleta.');
  }
};
