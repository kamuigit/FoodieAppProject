import { AbstractControl, FormControl } from '@angular/forms';

export class CustomValidator {
  static checkPasswords(a: AbstractControl) {
    if (a.get('password')?.value === a.get('confirmPassword')?.value) {
      return null;
    } else {
      console.log(a.get('password')?.value);
      console.log(a.get('confirmPassword')?.value);
      return { passwordVerify: true };
    }
  }

  static AgeVerify(a: FormControl) {
    if (a.value !== '') {
      if (a.value >= 13) {
        return null;
      } else {
        return { ageCheck: true };
      }
    }
    return null;
  }
}
