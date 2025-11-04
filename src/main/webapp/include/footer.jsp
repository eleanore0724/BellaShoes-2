<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<br>

<footer class="bg-white fixed-bottom">
        <div class="container d-flex flex-column flex-md-row justify-content-between align-items-center gap-3">
		<br> <br>
		<div class="text-secondary small">© 2025 BB Shoes</div>
            <ul class="nav">
                <li class="nav-item"><a class="nav-link px-2 text-secondary" href="#">隱私權</a></li>
                <li class="nav-item"><a class="nav-link px-2 text-secondary" href="#">服務條款</a></li>
            </ul>
        </div>
    </footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<script>
document.addEventListener("DOMContentLoaded", () => {
	  document.querySelectorAll('.btn-add-cart').forEach(button => {
	      button.addEventListener('click', function() {
	          const form = this.closest('.add-to-cart-form');
	          const formData = new FormData(form);

	          fetch('addToCartServlet', {
	              method: 'POST',
	              body: formData
	          })
	          .then(res => res.json())
	          .then(data => {
	              console.log("回傳資料：", data);
	              if (data.status === "success") {
	                  document.getElementById("cartCount").textContent = data.cartCount;
	                  this.textContent = "已加入";
	                  setTimeout(() => this.textContent = "加入購物車", 1000);
	              } else {
	                  alert("加入購物車失敗");
	              }
	          })
	          .catch(err => {
	              console.error("加入購物車錯誤：", err);
	          });
	      });
	  });
	});
	
async function updateCart(productId, action) {
    const response = await fetch('updateCartAjax', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `productId=${productId}&action=${action}`
    });

    const data = await response.json();
    if (data.success) {
        // 更新畫面數量與總價
        const row = document.querySelector(`tr[data-product-id="${productId}"]`);
        if (data.newQuantity > 0) {
            row.querySelector('.qty').textContent = data.newQuantity;
        } else {
            row.remove(); // 數量 0 自動刪除列
        }
        document.getElementById('totalPrice').textContent = data.totalPrice.toFixed(2);
    } else {
        alert('更新失敗：' + data.message);
    }
}

//綁定按鈕事件
document.querySelectorAll('.btn-plus').forEach(btn => {
    btn.addEventListener('click', e => {
        const productId = e.target.closest('tr').dataset.productId;
        updateCart(productId, 'plus');
    });
});
document.querySelectorAll('.btn-minus').forEach(btn => {
    btn.addEventListener('click', e => {
        const productId = e.target.closest('tr').dataset.productId;
        updateCart(productId, 'minus');
    });
});

//清空購物車
document.getElementById('clearCartBtn').addEventListener('click', async () => {
    if (!confirm('確定清空購物車？')) return;
    const res = await fetch('clearCartAj
    		ax', { method: 'POST' });
    const data = await res.json();
    if (data.success) {
        document.querySelector('#cartTable tbody').innerHTML = '';
        document.getElementById('totalPrice').textContent = '0';
    } else {
        alert('清空失敗');
    }
});

</script>