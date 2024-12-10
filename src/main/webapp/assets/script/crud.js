const dialog = document.querySelector('.add-user');
const modalEditUser = document.querySelector('.edit-user');
const showButton = document.getElementById('add-user');
const closeButton = document.querySelector('dialog button');
const $closeBtnEdit = document.querySelector('.modal__close--edit');

const $formAddUser = document.querySelector('.add-user__form');
const $formEditUser = document.querySelector('.edit-user__form');
const $tableUsers = document.querySelector('.table__body');
const $btnDeleteUser = document.querySelector('.table__action--delete');

// "Show the dialog" button opens the dialog modally
showButton.addEventListener('click', () => {
  dialog.showModal();
});

// "Close" button closes the dialog
closeButton.addEventListener('click', () => {
  dialog.close();
});
$closeBtnEdit.addEventListener('click', () => {
  modalEditUser.close();
});

const userDate = [];

$tableUsers.querySelectorAll("tr")
  .forEach(
    tr => {
      let row = {}
      tr.querySelectorAll("td").forEach((td, index) => {
        switch (index) {
          case 0:
            row["id"] = parseInt(td.textContent, 10);
            break;
          case 1:
            row["name"] = td.textContent;
            break;
          case 2:
            row["email"] = td.textContent;
            break;
          case 3:
            row["role"] = td.textContent;
            break;
          case 4:
            row["permission"] = td.textContent;
            break;
        }
      })
      userDate.push(row)
    }
  )
document.querySelectorAll('.table__action--delete').forEach((btn) => {
  btn.addEventListener('click', (e) => {
    const id = e.target.getAttribute('id');
    const userId = id.split('--')[1];
    const data = `id=${userId}`
    window.location.href=`users/delete?id=${userId}`
    console.log(`Eliminar el usuario ${userId}`)
  });
});

document.querySelectorAll('.table__action--edit').forEach((btn) => {
  btn.addEventListener('click', (e) => {
    const id = e.target.getAttribute('id');
    const userId = parseInt(id.split('--')[1], 10);
    $formEditUser.reset();
    editUser(userId)
  });
});

/* const addUser = (e) => {
 e.preventDefault();
 const newUser = Object.fromEntries(new FormData(e.target));
 if (!newUser.id) newUser.id = Id++;
 userDate.push(newUser);
 printDataUser(userDate);
 dialog.close();
 $formAddUser.reset();
};
const deleteUser = (id) => {
 const userIndex = userDate.findIndex((user) => user.id === id);
 if (userIndex !== -1) {
  userDate.splice(userIndex, 1);
  printDataUser(userDate);
 }
}; */

const editUser = (id) => {

  const userToEdit = userDate.find((user) => user.id === id);
  console.log(userToEdit);
  $formEditUser.querySelector('input[name="userName"]').value =
    userToEdit.name;
  $formEditUser.querySelector('input[name="email"]').value = userToEdit.email;
  $formEditUser.querySelector('select[name="rol"]').value = userToEdit.role;
  $formEditUser.querySelector('select[name="permissions"]').value =
    $formEditUser.querySelector('input[name="userId"]').value =
    userToEdit.id;

  modalEditUser.showModal();

  $formEditUser.onsubmit = (e) => {
    modalEditUser.close();
  };
};

// $formAddUser.addEventListener('submit', addUser);
